package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.dto.pedidoItens.PedidoItensRequestDTO;
import br.com.serratec.ecommerce.dto.pedidoItens.PedidoItensResponseDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.PedidoItens;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.repository.PedidoRespository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRespository pedidoRepository;

    @Autowired
    private PedidoItensService pedidoItensService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;

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

        if(optPedido.isEmpty()) {
            throw new RuntimeException("Nenhum pedido encontrado com o ID: " + id);
        }

        return mapper.map(optPedido.get(), PedidoResponseDTO.class);
    }

    @Transactional
    public PedidoResponseDTO adicionar(PedidoRequestDTO pedidoRequest) {
        
        Pedido pedidoModel = adicionarPedido(pedidoRequest);
        pedidoRequest.setId(pedidoModel.getIdPedido());

        List<PedidoItens> pedidoItens = adicionarPedidoItens(pedidoRequest.getPedidoItens(), pedidoModel);

        pedidoModel.setPedidoItens(pedidoItens);

        return mapper.map(pedidoModel, PedidoResponseDTO.class);
    }

    public PedidoResponseDTO atualizar(long id, PedidoRequestDTO pedidoRequest) {
        obterPorId(id);

        pedidoRequest.setId(id);

        Pedido pedidoModel = pedidoRepository.save(mapper.map(pedidoRequest, Pedido.class));   



        return mapper.map(pedidoModel, PedidoResponseDTO.class);
    }

    public void deletar(long id) {
        obterPorId(id);
        pedidoRepository.deleteById(id);
    }
 
    private Pedido adicionarPedido(PedidoRequestDTO pedidoRequest) {

        Pedido pedidoModel = mapper.map(pedidoRequest, Pedido.class);
        pedidoModel.setIdPedido(0);
        pedidoModel.setDataCompra();
        UsuarioResponseDTO usuarioResponse = usuarioService.obterPorId(pedidoRequest.getIdUsuario());
        pedidoModel.setUsuario(mapper.map(usuarioResponse, Usuario.class));
        pedidoModel = pedidoRepository.save(pedidoModel);

        return pedidoModel;
    }

    private List<PedidoItens> adicionarPedidoItens(List<PedidoItensRequestDTO> pedidoItensRequest, Pedido pedidoModel) {

        List<PedidoItens> adicionados = new ArrayList<>();
      
        for (PedidoItensRequestDTO pedidoItemRequest : pedidoItensRequest){

            PedidoItens pedidoItem = mapper.map(pedidoItemRequest, PedidoItens.class);

            pedidoItem.setPedido(pedidoModel);

            pedidoItensService.adicionar(mapper.map(pedidoItem, PedidoItensRequestDTO.class));



/* 
            pedidoItemRequest.setIdPedido(pedidoRequest.getId());

            pedidoItemResponse = pedidoItensService.adicionar(pedidoItemRequest);

            adicionados.add(mapper.map(pedidoItemResponse, PedidoItens.class));
*/
        }

        return adicionados;
    }

}
