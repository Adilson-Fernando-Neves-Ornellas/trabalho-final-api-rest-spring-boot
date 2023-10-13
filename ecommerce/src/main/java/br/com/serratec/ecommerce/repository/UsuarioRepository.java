package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
