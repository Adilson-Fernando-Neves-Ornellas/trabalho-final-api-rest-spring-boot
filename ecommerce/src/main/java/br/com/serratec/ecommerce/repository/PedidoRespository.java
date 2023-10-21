package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.ecommerce.model.Pedido;

public interface PedidoRespository extends JpaRepository<Pedido, Long>{
    
}
