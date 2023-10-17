package br.com.serratec.ecommerce.dto.pedido;

import java.util.List;

import br.com.serratec.ecommerce.dto.pedidoItens.PedidoItensRequestDTO;

public class PedidoRequestDTO extends PedidoBaseDTO{
    
    private long idUsuario;
    private List<PedidoItensRequestDTO> pedidoItensRequest;

    public List<PedidoItensRequestDTO> getPedidoItens() {
        return pedidoItensRequest;
    }

    public void setPedidoItens(List<PedidoItensRequestDTO> pedidoItensRequest) {
        this.pedidoItensRequest = pedidoItensRequest;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
