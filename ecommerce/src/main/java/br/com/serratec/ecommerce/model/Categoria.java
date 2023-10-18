package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.serratec.ecommerce.model.exceptions.ResourceBadRequestException;


@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private long idCategoria;

    @Column(nullable = false, unique = true)
    private String nmCategoria;

    @Column(nullable = false)
    private String descricao;

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

    public void validarNome() {
        if (nmCategoria.equals("") || descricao.equals("")) {
            throw new ResourceBadRequestException("Nome da categoria e descrição obrigatórios");
        }
    }

}
