
package br.com.serratec.ecommerce.dto.usuario;

import java.util.Date;

public class UsuarioResponseDTO extends UsuarioBaseDTO{
   
    private Date dataCadastro; 
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}

