package br.com.serratec.ecommerce.dto.pedidoItens;

public class PedidoItensResponseDTO extends PedidoItensBaseDTO{
    
    private double valorTotal;
    private long idPedidoItens;

    public long getIdPedidoItens() {
        return idPedidoItens;
    }

    public void setIdPedidoItens(long idPedidoItens) {
        this.idPedidoItens = idPedidoItens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
  
}
