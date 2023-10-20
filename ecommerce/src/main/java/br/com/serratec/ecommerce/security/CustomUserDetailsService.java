package br.com.serratec.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.serratec.ecommerce.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //metodo que vai obter dinamicamente o usuario no banco pelo username que no nosso caso é o email
        return usuarioRepository.findByEmail(username).get();
    }
    
    public UserDetails obterUsuarioPeloId(long id){
        return usuarioRepository.findById(id).get();
    }
}
