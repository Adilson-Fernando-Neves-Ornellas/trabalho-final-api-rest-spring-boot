package br.com.serratec.ecommerce.dto.usuario;



import br.com.serratec.ecommerce.enums.Perfil;

public abstract class UsuarioBaseDTO {
   

    private String nmUsuario;

    private String login;

    private Perfil perfil;

    private String fotoUsuario;


    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    } 

    
}
