package br.com.serratec.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.ecommerce.enums.TipoEntidade;
import br.com.serratec.ecommerce.model.Auditoria;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.repository.AuditoriaRepository;


@Service
public class AuditoriaService {
    @Autowired
    private AuditoriaRepository auditoriaRepository;

    public void registrarAuditoria(Auditoria auditoria){
        this.auditoriaRepository.save(auditoria);
    }

   public void infoRegistarAuditoria(TipoEntidade entidade,String movimentacao, Object vlOriginal, Object vlAtualizado){
        try {
            // Pegando o usuario authenticado para auditoria
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Auditoria auditoria = new Auditoria(
            entidade,
            movimentacao, 
            new ObjectMapper().writeValueAsString(vlOriginal), 
            new ObjectMapper().writeValueAsString(vlAtualizado),
            usuario);

            registrarAuditoria(auditoria);

        } catch (Exception e) {

        }
    }
}