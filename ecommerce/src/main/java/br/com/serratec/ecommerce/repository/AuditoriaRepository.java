package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.ecommerce.model.Auditoria;

public interface AuditoriaRepository extends JpaRepository<Auditoria ,Long> {
    
}
