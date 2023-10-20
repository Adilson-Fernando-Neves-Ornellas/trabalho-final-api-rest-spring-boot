package br.com.serratec.ecommerce.dto.pedido;

import java.util.List;
import br.com.serratec.ecommerce.dto.pedidoItens.PedidoItensResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioPedidoDTO;

public class PedidoResponseDTO extends PedidoBaseDTO{
    
    private Long id;
    private UsuarioPedidoDTO usuario;
    private List<PedidoItensResponseDTO> pedidoItens;

    public List<PedidoItensResponseDTO> getPedidoItens() {
        return pedidoItens;
    }
    
    public void setPedidoItens(List<PedidoItensResponseDTO> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioPedidoDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPedidoDTO usuario) {
        this.usuario = usuario;
    }

}
