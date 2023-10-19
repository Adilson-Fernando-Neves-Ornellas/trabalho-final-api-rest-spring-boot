package br.com.serratec.ecommerce.dto.categoria;

import java.util.List;

import br.com.serratec.ecommerce.model.Produto;

public class CategoriaResponseDTO extends CategoriaRequestDTO{

    private long idCategoria;

    private List<Produto> produtos;


    public CategoriaResponseDTO(){
        
    }

    public long getIdCategoria() {
        return idCategoria;
    }


    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    

}
