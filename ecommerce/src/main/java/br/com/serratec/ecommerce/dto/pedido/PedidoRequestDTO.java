package br.com.serratec.ecommerce.dto.pedido;

import java.util.List;

import br.com.serratec.ecommerce.model.PedidoItens;

public class PedidoRequestDTO extends PedidoBaseDTO{
    
    private long id;
    private long idUsuario;
    private List<PedidoItens> pedidoItens;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public List<PedidoItens> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(List<PedidoItens> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
