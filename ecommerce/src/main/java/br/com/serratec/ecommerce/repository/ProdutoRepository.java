package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
