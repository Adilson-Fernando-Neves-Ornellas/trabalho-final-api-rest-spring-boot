package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.serratec.ecommerce.dto.auditoria.AuditoriaResponseDTO;
import br.com.serratec.ecommerce.enums.TipoEntidade;
import br.com.serratec.ecommerce.model.Auditoria;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.model.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.AuditoriaRepository;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void registrarAuditoria(Auditoria auditoria) {
        this.auditoriaRepository.save(auditoria);
    }

    public void infoRegistarAuditoria(TipoEntidade entidade, String movimentacao, Object vlOriginal,
            Object vlAtualizado) {
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

    public List<AuditoriaResponseDTO> obterAuditoria() {

        return auditoriaRepository.findAll()
                .stream()
                .map(auditoria -> modelMapper.map(auditoria, AuditoriaResponseDTO.class))
                .collect(Collectors.toList());

    }

    public AuditoriaResponseDTO obterPorId(long id) {

        return modelMapper.map(auditoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontardo para o id: " + id)),
                AuditoriaResponseDTO.class);
    }
}