package br.com.serratec.ecommerce.dto.categoria;

import java.util.List;
import br.com.serratec.ecommerce.dto.produto.ProdutoCategoriaDTO;

public class CategoriaResponseDTO extends CategoriaRequestDTO{

    private long idCategoria;
    private List<ProdutoCategoriaDTO> produtos;


    public CategoriaResponseDTO(){
        
    }

    public long getIdCategoria() {
        return idCategoria;
    }


    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<ProdutoCategoriaDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoCategoriaDTO> produtos) {
        this.produtos = produtos;
    }

}
