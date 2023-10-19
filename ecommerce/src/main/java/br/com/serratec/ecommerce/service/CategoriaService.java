package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.serratec.ecommerce.dto.categoria.CategoriaRequestDTO;
import br.com.serratec.ecommerce.dto.categoria.CategoriaResponseDTO;
import br.com.serratec.ecommerce.enums.TipoEntidade;
import br.com.serratec.ecommerce.model.Categoria;
import br.com.serratec.ecommerce.model.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private ModelMapper modelMapper;

    public List<CategoriaResponseDTO> obterCategoria() {

        return categoriaRepository.findAll()
                .stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaResponseDTO.class))
                .collect(Collectors.toList());

    }

    public CategoriaResponseDTO obterPorId(long id) {

        return modelMapper.map(categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum registro encontardo para o id: " + id)),
                CategoriaResponseDTO.class);
    }

    public CategoriaResponseDTO adicionar(CategoriaRequestDTO categoriaRequest) {

        Categoria categoriaModel = modelMapper.map(categoriaRequest, Categoria.class);

        auditoriaService.infoRegistarAuditoria(TipoEntidade.CATEGORIA, "Cadastro", "", categoriaModel);

        return modelMapper.map(categoriaRepository.save(categoriaModel), CategoriaResponseDTO.class);

    }

    public CategoriaResponseDTO atualizar(long id, CategoriaRequestDTO categoriaDTO) {

        
        Categoria categoria = categoriaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontardo para o id: " + id));
        
        categoria.setDescricao(categoriaDTO.getDescricao());
        categoria.setNmCategoria(categoriaDTO.getNmCategoria());
        categoriaRepository.save(categoria);

        // acha o categoriaResponse por Id e salva a Auditoria dele.
        var catBanco = obterPorId(id);
        auditoriaService.infoRegistarAuditoria(TipoEntidade.CATEGORIA, "Atualizar", catBanco , categoria);

        return modelMapper.map(categoria, CategoriaResponseDTO.class);

    }

    public void deletar(Long id) {
        obterPorId(id);
        categoriaRepository.deleteById(id);
    }

}
