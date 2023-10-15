package br.com.serratec.ecommerce.dto.produto;

public class ProdutoRequestDTO extends ProdutoBaseDTO {

    private boolean statusProd;

    public boolean isStatusProd() {
        return statusProd;
    }

    public void setStatusProd(boolean statusProd) {
        this.statusProd = statusProd;
    }

}
