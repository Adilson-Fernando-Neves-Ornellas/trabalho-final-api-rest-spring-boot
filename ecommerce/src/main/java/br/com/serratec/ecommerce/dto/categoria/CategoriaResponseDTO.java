package br.com.serratec.ecommerce.dto.categoria;


public class CategoriaResponseDTO extends CategoriaRequestDTO{

    private long idCategoria;


    public CategoriaResponseDTO(){
        
    }

    public long getIdCategoria() {
        return idCategoria;
    }


    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

}
