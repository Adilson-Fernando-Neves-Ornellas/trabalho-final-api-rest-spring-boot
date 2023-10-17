package br.com.serratec.ecommerce.model;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.serratec.ecommerce.enums.Perfil;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private long id;

    @Column(nullable = false)
    private String nmUsuario;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private Date dataCadastro;

    //@Column(nullable = false)
    private Perfil perfil;

    private String fotoUsuario;

    // @OneToMany(mappedBy = "usuario")
    // @JsonBackReference
    // private List<Pedido> pedidos;

    // @OneToMany(mappedBy = "usuario")
    // private Log log;
   
    public Usuario(){
        this.dataCadastro = new Date();
    }   

    public Usuario(long id, String nmUsuario, String login, String senha, String email, String telefone,
            Perfil perfil, String fotoUsuario) {
        this.id = id;
        this.nmUsuario = nmUsuario;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = new Date();
        this.perfil = perfil;
        this.fotoUsuario = fotoUsuario;
       
    }

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
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
    
    public Date getDataCadastro() {
        return dataCadastro;
    }
    
    public void setDataCadastro() {
        this.dataCadastro = new Date();
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

    // public List<Pedido> getPedido() {
    //     return pedidos;
    // }

    // public void setPedido(List<Pedido> pedidos) {
    //     this.pedidos = pedidos;
    // }
    
    }
    