package br.com.serratec.ecommerce.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public UsuarioResponseDTO adcionar(UsuarioRequestDTO usuarioRequest){
        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);
        usuario.setId(0);

        // Criptografando a senha antes de salvar no bd
        usuario.setSenha(BCrypt.withDefaults().hashToString(12, usuario.getSenha().toCharArray()));
        usuario.setDataCadastro();
        
        usuario = usuarioRepository.save(usuario);

        return modelMapper.map(usuario, UsuarioResponseDTO.class);
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
    
}
