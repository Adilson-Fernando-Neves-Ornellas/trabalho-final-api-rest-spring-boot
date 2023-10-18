package br.com.serratec.ecommerce.dto.categoria;


public class CategoriaResponseDTO {

    private long idCategoria;
    private String nmCategoria;
    private String descricao;
    


    public CategoriaResponseDTO(){
        
    }

    public long getIdCategoria() {
        return idCategoria;
    }


    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }


    public String getNmCategoria() {
        return nmCategoria;
    }


    public void setNmCategoria(String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
