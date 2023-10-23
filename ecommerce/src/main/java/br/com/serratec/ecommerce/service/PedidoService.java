package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.PedidoItens;
import br.com.serratec.ecommerce.model.exceptions.ResourceBadRequestException;
import br.com.serratec.ecommerce.model.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.PedidoRespository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRespository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    public ModelMapper mapper;

    public List<PedidoResponseDTO> obterTodos() {

        return pedidoRepository.findAll()
                .stream()
                .map(pedido -> mapper.map(pedido, PedidoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO obterPorId(long id) {
        Optional<Pedido> optPedido = pedidoRepository.findById(id);

        if (optPedido.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum pedido encontrado com o ID: " + id);
        }

        return mapper.map(optPedido.get(), PedidoResponseDTO.class);
    }

    @Transactional
    public PedidoResponseDTO adicionar(PedidoRequestDTO pedido) {

        usuarioService.obterPorId(pedido.getIdUsuario());
        if (!usuarioService.verificarSatusUsuario(pedido.getIdUsuario())) {
            throw new RuntimeException("O usuário com ID: " + pedido.getIdUsuario() + " está desativado.");
        }

        // Antes de salvar o pedido, associe os itens ao pedido
        Pedido pedidoModel = mapper.map(pedido, Pedido.class);

        for (PedidoItens item : pedidoModel.getItens()) {
            item = adicionarValorTotalItem(item);
            item.setPedido(pedidoModel);
        }

        pedidoModel = adicionarValorFinal(pedidoModel);

        pedidoModel = pedidoRepository.save(pedidoModel);
        return mapper.map(pedidoModel, PedidoResponseDTO.class);
    }

    public PedidoResponseDTO atualizar(long id, PedidoRequestDTO pedidoRequest) {

        Optional<Pedido> optPedido = pedidoRepository.findById(id);

        if (optPedido.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum pedido encontrado com o ID: " + id);
        }

        Pedido pedidoBanco = optPedido.get();

        // Iterar sobre os itens do pedido na requisição
        for (PedidoItens itemRequest : pedidoRequest.getPedidoItens()) {
            // Verificar se o item já existe no pedido
            Optional<PedidoItens> itemOptional = pedidoBanco.getItens()
                    .stream()
                    .filter(item -> item.getIdPedidoItens() == itemRequest.getIdPedidoItens())
                    .findFirst();

            if (itemOptional.isPresent()) {
                // Atualizar o item existente
                PedidoItens itemBanco = itemOptional.get();
                itemBanco.setQuantidade(itemRequest.getQuantidade());

                // Atualizar o valor total do item
                itemBanco = adicionarValorTotalItem(itemBanco);
            } else {
                // Adicionar o novo item à lista de itens do pedido
                PedidoItens newItem = mapper.map(itemRequest, PedidoItens.class);
                newItem = adicionarValorTotalItem(newItem);
                newItem.setPedido(pedidoBanco);
                pedidoBanco.getItens().add(newItem);
            }
        }

        // Remover itens com quantidade menor ou igual a 0
        pedidoBanco.getItens().removeIf(item -> item.getQuantidade() <= 0);

        // Recalcular o valor final do pedido
        pedidoBanco = adicionarValorFinal(pedidoBanco);

        // Salvar o pedido atualizado no banco de dados
        Pedido pedidoModel = pedidoRepository.save(pedidoBanco);
        return mapper.map(pedidoModel, PedidoResponseDTO.class);
    }

    public void deletar(long id) {
        obterPorId(id);

        pedidoRepository.deleteById(id);
    }

    private PedidoItens adicionarValorTotalItem(PedidoItens item) {
        long idProd = item.getProduto().getIdProd();
        ProdutoResponseDTO produtoResponse = produtoService.obterPorId(idProd);

        double estoqueAtual = produtoResponse.getEstoqueProd();

        if (estoqueAtual < item.getQuantidade() || produtoResponse.getStatusProd() == false) {
            throw new ResourceBadRequestException(
                    "O produto com ID: " + idProd + " não possui estoque suficiente ou está indisponível");
        }
        item.setVlUnitario(produtoResponse.getValorProd());

        item.setValorTotal(
                (produtoResponse.getValorProd() * ((item.getAcresProduto() / 100 + 1) - (item.getDescProduto() / 100)))
                        * item.getQuantidade());

        produtoResponse.setEstoqueProd(produtoResponse.getEstoqueProd() - item.getQuantidade());

        produtoService.atualizar(idProd, mapper.map(produtoResponse, ProdutoRequestDTO.class));

        return item;
    }

    private Pedido adicionarValorFinal(Pedido pedido) {

        double valorTotal = 0;

        for (PedidoItens item : pedido.getItens()) {
            valorTotal += item.getValorTotal();
        }

        pedido.setValorFinal(valorTotal * ((pedido.getAcrescimoTotal() / 100 + 1) - (pedido.getDescontoTotal() / 100)));

        return pedido;
    }

}
