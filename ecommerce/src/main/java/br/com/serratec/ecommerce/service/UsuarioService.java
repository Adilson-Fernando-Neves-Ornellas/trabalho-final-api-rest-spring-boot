package br.com.serratec.ecommerce.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ModelMapper modelMapper;

    public List<UsuarioResponseDTO> obterTodos(){
        return usuarioRepository.findAll()
            .stream()
            .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
            .collect(Collectors.toList());
    }

    public UsuarioResponseDTO obterPorId(Long id){
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if(optUsuario.isEmpty()){
            throw new RuntimeException("Nenhum registro encontardo para o id: "+ id);
        }
        return modelMapper.map(optUsuario.get(), UsuarioResponseDTO.class);
    }

    @Transactional
    public UsuarioResponseDTO adcionar(UsuarioRequestDTO usuarioRequest){
        
        Usuario usuarioModel = adicionUsuario(usuarioRequest);
       
        //List<Pedido> pedidos = adicionarPedidos(usuarioRequest.getPedidos(), usuarioModel);
        //usuarioModel.setPedido(pedidos);

        return modelMapper.map(usuarioModel, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioRequest){
       
        obterPorId(id);
        
        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);
        usuario.setId(id);

        return modelMapper.map(usuarioRepository.save(usuario), UsuarioResponseDTO.class);

    }

    public void deletar(long id){
        obterPorId(id);
        usuarioRepository.deleteById(id);
    }
    
    private Usuario adicionUsuario(UsuarioRequestDTO usuarioRequest){
        Usuario usuarioModel = modelMapper.map(usuarioRequest, Usuario.class);
        usuarioModel.setId(0);
        usuarioModel = usuarioRepository.save(usuarioModel);
        return usuarioModel;
    }

    // private List<Pedido> adicionarPedidos(List<PedidoRequestDTO> pedRequest, Usuario usuarioModel){
        
    //     List<Pedido> adicionados = new ArrayList<>();

    //     for(PedidoRequestDTO pedidoResquest : pedRequest){
    //         Pedido pedido = modelMapper.map(pedidoResquest, Pedido.class);

    //         pedido.setUsuario(usuarioModel);

    //         pedidoService.adicionar(modelMapper.map(pedido, PedidoRequestDTO.class));

    //         adicionados.add(pedido);
    //     }
    //     return adicionados;
    // }
}
