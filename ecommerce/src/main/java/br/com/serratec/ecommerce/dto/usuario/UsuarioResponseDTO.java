
package br.com.serratec.ecommerce.dto.usuario;

import java.util.Date;

public class UsuarioResponseDTO extends UsuarioBaseDTO{
   
    private Date dataCadastro; 

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}

