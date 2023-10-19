package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

   public void registrarAuditoria1(String entidade,String movimentacao, Object vlOriginal, Object vlAtualizado){
        // Depois de atualizar, gravar a auditoria.
        try {

            // Pegando o usuario authenticado para auditoria
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Auditoria auditoria = new Auditoria(
            TipoEntidade.valueOf(entidade),
            movimentacao, 
            new ObjectMapper().writeValueAsString(vlOriginal), 
            new ObjectMapper().writeValueAsString(vlAtualizado),
            usuario);

            this.auditoriaRepository.save(auditoria);

        } catch (Exception e) {

        }
    }
}