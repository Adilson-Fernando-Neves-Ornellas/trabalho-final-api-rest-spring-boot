package br.com.serratec.ecommerce.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import br.com.serratec.ecommerce.enums.TipoEntidade;

@Entity
public class Auditoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAuditoria;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Date dataAlteracao;

    @Column(nullable = false)
    private String movimentacao;

    @Column(nullable = false)
    private TipoEntidade entidade;

    @Column(nullable = false)
    private String vlOrinal;

    @Column(nullable = false)
    private String vlAtualizado;

    // constructs
    public Auditoria(TipoEntidade tipoEntidade, String movimentacao, String vlOrinal, 
    String vlAtualizado,Usuario usuario) {
        this.entidade = tipoEntidade;
        this.movimentacao = movimentacao;
        this.vlOrinal= vlOrinal;
        this.vlAtualizado = vlAtualizado;
        this.usuario = usuario;
        this.dataAlteracao= new Date();
    }

    public Auditoria() {
    }



    // get and set
    public long getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(long idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(String movimentacao) {
        this.movimentacao = movimentacao;
    }

    public TipoEntidade getEntidade() {
        return entidade;
    }

    public void setEntidade(TipoEntidade entidade) {
        this.entidade = entidade;
    }

    public String getVlOrinal() {
        return vlOrinal;
    }

    public void setVlOrinal(String vlOrinal) {
        this.vlOrinal = vlOrinal;
    }

    public String getVlAtualizado() {
        return vlAtualizado;
    }

    public void setVlAtualizado(String vlAtualizado) {
        this.vlAtualizado = vlAtualizado;
    }

}
