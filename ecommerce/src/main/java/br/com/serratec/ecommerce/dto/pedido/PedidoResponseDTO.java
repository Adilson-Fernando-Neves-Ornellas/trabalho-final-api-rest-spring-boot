package br.com.serratec.ecommerce.dto.pedido;

import java.util.List;

import br.com.serratec.ecommerce.model.PedidoItens;
import br.com.serratec.ecommerce.model.Usuario;

public class PedidoResponseDTO {
    
    private Usuario usuario;
    private List<PedidoItens> pedidoItens;

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<PedidoItens> getPedidoItens() {
        return pedidoItens;
    }
    public void setPedidoItens(List<PedidoItens> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }
}
