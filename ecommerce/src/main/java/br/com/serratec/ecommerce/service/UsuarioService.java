package br.com.serratec.ecommerce.service;

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
import br.com.serratec.ecommerce.dto.usuario.UsuarioLoginResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.model.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.UsuarioRepository;
import br.com.serratec.ecommerce.security.JWTService;
import br.com.serratec.ecommerce.utils.Utils;

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

    public void deletar(long id){
        obterPorId(id);
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDTO obterPorEmail(String email){
        Optional<Usuario> optUsuario =  usuarioRepository.findByEmail(email);

        return modelMapper.map(optUsuario.get(),UsuarioResponseDTO.class);
    }

    public UsuarioLoginResponseDTO logar(String email, String senha){
        Optional<Usuario> optUsuario =usuarioRepository.findByEmail(email);
        if(optUsuario.isEmpty()){
            throw new BadCredentialsException("Usuário ou senha invalidos");
        }
        Authentication autenticacao = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(email, senha,Collections.emptyList()));
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
            String token = BEARER + jwtService.gerarToken(autenticacao);
            UsuarioResponseDTO usuarioResponse = obterPorEmail(email);
            return new UsuarioLoginResponseDTO(token, usuarioResponse);
    }
}
