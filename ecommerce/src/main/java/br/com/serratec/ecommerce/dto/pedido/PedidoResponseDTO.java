package br.com.serratec.ecommerce.dto.pedido;

import java.util.List;

import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;

public class PedidoResponseDTO {
    
    private Long id;
    private UsuarioResponseDTO usuario;

    
    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }
    private List<PedidoResponseDTO> pedidoItens;

    public List<PedidoResponseDTO> getPedidoItens() {
        return pedidoItens;
    }
    public void setPedidoItens(List<PedidoResponseDTO> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
