package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioLoginResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.model.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.UsuarioRepository;
import br.com.serratec.ecommerce.security.JWTService;

@Service
public class UsuarioService  {

    private static final String BEARER = "Bearer ";
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

     @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ModelMapper modelMapper;

    
    public List<UsuarioResponseDTO> obterTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
            .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
            .collect(Collectors.toList());
    }
    
    public UsuarioResponseDTO obterPorId(Long id){
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if(optUsuario.isEmpty()){
            throw new ResourceNotFoundException("Nenhum registro encontardo para o id: "+ id);
        }
        return modelMapper.map(optUsuario.get(), UsuarioResponseDTO.class);
    }

    @Transactional
    public UsuarioResponseDTO adcionar(UsuarioRequestDTO usuarioRequest){
        
        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);
       
        String senha = passwordEncoder.encode(usuario.getPassword());

        usuario.setSenha(senha);
        usuario.setId(0);
        usuario.setStatusUsuario(true);

        usuario = usuarioRepository.save(usuario);

        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioRequest){
       
        obterPorId(id);
        
        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);
        usuario.setId(id);

        Usuario usuarioBanco = usuarioRepository.getReferenceById(id);

        //Utils.copyNonNullProperties(usuarioBanco, usuario);
        return modelMapper.map(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
    }

    public void deletar(long id) {
        
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isPresent()) {
            usuario.get().setStatusUsuario(false);
         usuarioRepository.save(usuario.get());
        }
    }    

    /**
     * Método para verificar se o usuário está ativo.
     * @param long Id do usuário.
     * @return TRUE se o usuário estiver ativo ou FALSE se ele estiver desativado.
     */
    public boolean verificarSatusUsuario(long id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.get().isStatusUsuario()) {
            return true;
        }

        return false;
    }

    public UsuarioResponseDTO obterPorEmail(String email){
        
        Optional<Usuario> optUsuario =  usuarioRepository.findByEmail(email);

        return modelMapper.map(optUsuario.get(),UsuarioResponseDTO.class);
    }

    public UsuarioLoginResponseDTO logar(String email, String senha){
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);
        if(optUsuario.isEmpty()){
            throw new BadCredentialsException("Usuário ou senha invalidos");
        }

        if(!verificarSatusUsuario(optUsuario.get().getId())){
            throw new RuntimeException("O usuário com e-mail: " + optUsuario.get().getEmail() + " está desativado.");
        }
        
        Authentication autenticacao = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(email, senha,Collections.emptyList()));
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
            String token = BEARER + jwtService.gerarToken(autenticacao);
            UsuarioResponseDTO usuarioResponse = obterPorEmail(email);
            return new UsuarioLoginResponseDTO(token, usuarioResponse);
    }

    public PedidoResponseDTO obterMeuPedidoPorId(Long id){
        PedidoResponseDTO meupedido = pedidoService.obterPorId(id);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(meupedido.getUsuario().getId() == usuario.getId())){
            throw new ResourceNotFoundException("Esse pedido não pertence a esse usuario");
        }
        return meupedido;
    }

    public List<PedidoResponseDTO> obterTodosPedidoNoMeuId(){
        List <PedidoResponseDTO> todosPedidos = pedidoService.obterTodos();
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List <PedidoResponseDTO> meusPedidosAtualizados = new ArrayList<>();
        for(PedidoResponseDTO pedido:todosPedidos){
        if(pedido.getUsuario().getId() == usuario.getId()){
            meusPedidosAtualizados.add(pedido);
        }
    }
    if(meusPedidosAtualizados.isEmpty()){
        throw new ResourceNotFoundException("Você não tem nenhum pedido.");   
    }
    return meusPedidosAtualizados;
    } 
}
