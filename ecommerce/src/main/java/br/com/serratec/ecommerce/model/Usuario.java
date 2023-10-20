package br.com.serratec.ecommerce.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import br.com.serratec.ecommerce.enums.Perfil;
import br.com.serratec.ecommerce.model.exceptions.ResourceBadRequestException;

@Entity
public class Usuario implements UserDetails{
    
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

    private Perfil perfil;

    private String fotoUsuario;

    @Column(nullable = false)
    private boolean statusUsuario;

    public Usuario(){
        this.dataCadastro = new Date();
    }   

    public Usuario(long id, String nmUsuario, String login, String senha, String email, String telefone,
            Perfil perfil, String fotoUsuario, boolean statusUsuario) {
        this.id = id;
        this.nmUsuario = nmUsuario;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = new Date();
        this.perfil = perfil;
        this.fotoUsuario = fotoUsuario;
        this.statusUsuario = statusUsuario;
    }

    public Long getId() {
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

    public boolean isStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(boolean statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> perfis = new ArrayList<>();
        perfis.add(perfil.toString());

        return perfis.stream()
                .map(per -> new SimpleGrantedAuthority(per))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public void validarPerfil(String s) { 
        
        EnumSet<Perfil> perfisValido = EnumSet.of(Perfil.CLIENTE, Perfil.ADMIN);
        
        if (!perfisValido.contains(perfil)) {
            throw new IllegalArgumentException("O perfil " + perfil + " não é válido.");
        }
    }   

    public void camposNulo() {
        if (nmUsuario.equals("")|| login.equals("")|| senha.equals("") || email.equals("") || telefone.equals("")) {
            throw new ResourceBadRequestException("Os campos não podem ser nulos.");
        }
    }
}
    