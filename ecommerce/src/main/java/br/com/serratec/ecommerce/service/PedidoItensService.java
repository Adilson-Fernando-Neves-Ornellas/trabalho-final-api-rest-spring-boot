package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.serratec.ecommerce.dto.pedidoItens.PedidoItensRequestDTO;
import br.com.serratec.ecommerce.dto.pedidoItens.PedidoItensResponseDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.model.PedidoItens;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.repository.PedidoItensRepository;

@Service
public class PedidoItensService {
    
    @Autowired
    private PedidoItensRepository pedidoItensRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    public List<PedidoItensResponseDTO> obterTodos() {

        return pedidoItensRepository.findAll()
                .stream()
                .map(pedidoItens -> modelMapper.map(pedidoItens, PedidoItensResponseDTO.class))
                .collect(Collectors.toList());
    }

    public PedidoItensResponseDTO obterPorId(Long id) {
        Optional<PedidoItens> optPedidoItens = pedidoItensRepository.findById(id);

        if (optPedidoItens.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontardo para o id: " + id);
        }
        return modelMapper.map(optPedidoItens.get(), PedidoItensResponseDTO.class);
    }

    @Transactional
    public PedidoItensResponseDTO adicionar(PedidoItensRequestDTO pedidoItemRequest) {

        PedidoItens pedidoItem = modelMapper.map(pedidoItemRequest, PedidoItens.class);

        Produto produto = modelMapper.map(produtoService.obterPorId(pedidoItemRequest.getIdProduto()), Produto.class);

        pedidoItem.setValorTotal(produto.getValorProd() * ((pedidoItem.getAcresProduto() / 100 + 1) - (pedidoItem.getDescProduto() / 100)));

        pedidoItem.setIdPedidoItens(0l);

        pedidoItem = pedidoItensRepository.save(pedidoItem);

        produto.setEstoqueProd((produto.getEstoqueProd() - pedidoItem.getQuantidade()));

        produtoService.atualizar(produto.getIdProd(), modelMapper.map(produto, ProdutoRequestDTO.class));

        return modelMapper.map(pedidoItem, PedidoItensResponseDTO.class);
    }

    public PedidoItensResponseDTO atualizar(Long id, PedidoItensRequestDTO pedidoItemRequest) {
        obterPorId(id);

        PedidoItens pedidoItem = modelMapper.map(pedidoItemRequest, PedidoItens.class);
        pedidoItem.setIdPedidoItens(id);

        return modelMapper.map(pedidoItem, PedidoItensResponseDTO.class);
    }

    public void deletar(long id) {
        obterPorId(id);
        pedidoItensRepository.deleteById(id);
    }
    
}
