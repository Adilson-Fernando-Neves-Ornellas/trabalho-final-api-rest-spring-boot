package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.model.Log;

public interface LogRepository extends JpaRepository<Log ,Long> {
    
}
