
package br.com.serratec.ecommerce.dto.usuario;

public class UsuarioRequestDTO extends UsuarioBaseDTO{

    private String senha;
    private String email;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

