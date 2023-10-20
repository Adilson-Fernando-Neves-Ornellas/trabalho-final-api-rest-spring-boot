package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.ecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria,Long>{

    
}
