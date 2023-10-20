package br.com.serratec.ecommerce.dto.auditoria;

import java.util.Date;
import br.com.serratec.ecommerce.enums.TipoEntidade;
import br.com.serratec.ecommerce.model.Usuario;

public class AuditoriaResponseDTO {

    private long idAuditoria;

    private Usuario usuario;

    private Date dataAlteracao;

    private String movimentacao;

    private TipoEntidade entidade;

    private String vlOrinal;

    private String vlAtualizado;

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
