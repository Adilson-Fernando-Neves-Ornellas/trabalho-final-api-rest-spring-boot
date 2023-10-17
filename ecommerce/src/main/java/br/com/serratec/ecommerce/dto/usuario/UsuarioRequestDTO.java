
package br.com.serratec.ecommerce.dto.usuario;

//import java.util.List;

//import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;

public class UsuarioRequestDTO extends UsuarioBaseDTO{

    private String senha;

    private String email;

    private String telefone;

    //private List<PedidoRequestDTO> pedidos;

    // public List<PedidoRequestDTO> getPedidos() {
    //     return pedidos;
    // }

    // public void setPedidos(List<PedidoRequestDTO> pedidos) {
    //     this.pedidos = pedidos;
    // }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    

}

