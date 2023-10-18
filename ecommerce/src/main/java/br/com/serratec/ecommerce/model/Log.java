package br.com.serratec.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




@Entity
public class Log {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLod;

    @Column(nullable = false)
    private long idUsuario;

    @Column(nullable = false)
    private Date dataAlteracao;

    @Column(nullable = false)
    private String movimentacao;

    @Column(nullable = false)
    private String tabelaAlterada;

    @Column(nullable = false)
    private String jsonAntigo;

    @Column(nullable = false)
    private String jsonNovo;

    
    
    public Log(long idLod, long idUsuario, Date dataAlteracao, String movimentacao, String tabelaAlterada,
            String jsonAntigo, String jsonNovo) {
        this.idLod = idLod;
        this.idUsuario = idUsuario;
        this.dataAlteracao = dataAlteracao;
        this.movimentacao = movimentacao;
        this.tabelaAlterada = tabelaAlterada;
        this.jsonAntigo = jsonAntigo;
        this.jsonNovo = jsonNovo;
    }
    
    public Log() {
    }
    
    public long getIdLod() {
        return idLod;
    
    }
    public void setIdLod(long idLod) {
        this.idLod = idLod;
    
    }
    public long getIdUsuario() {
        return idUsuario;
    
    }
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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
    
    public String getTabelaAlterada() {
        return tabelaAlterada;
    }
    
    public void setTabelaAlterada(String tabelaAlterada) {
        this.tabelaAlterada = tabelaAlterada;
    }
    
    public String getJsonAntigo() {
        return jsonAntigo;
    }
    
    public void setJsonAntigo(String jsonAntigo) {
        this.jsonAntigo = jsonAntigo;
    }
    
    public String getJsonNovo() {
        return jsonNovo;
    }
    
    public void setJsonNovo(String jsonNovo) {
        this.jsonNovo = jsonNovo;
    }

    
    
}
