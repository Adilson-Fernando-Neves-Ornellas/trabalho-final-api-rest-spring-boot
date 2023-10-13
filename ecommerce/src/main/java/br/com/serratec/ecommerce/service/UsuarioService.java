package br.com.serratec.ecommerce.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.repository.UsuarioRepository;
import br.com.serratec.ecommerce.model.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obterTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario obterPorId(Long id){
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if(optUsuario.isEmpty()){
            throw new RuntimeException("Nenhum registro encontardo para o id: "+ id);
        }
        return optUsuario.get();
    }

    public Usuario adcionar(Usuario usuario){
        usuario.setId(0);
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario atualizar(Long id, Usuario usuario){
        obterPorId(id);
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void deletar(long id){
        obterPorId(id);
        usuarioRepository.deleteById(id);
    }
    
}
