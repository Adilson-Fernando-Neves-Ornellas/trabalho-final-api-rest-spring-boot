package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.serratec.ecommerce.model.Log;
import br.com.serratec.ecommerce.repository.LogRepository;

@Service
public class LogService {
    
    @Autowired
    private LogRepository logRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Long> obterTodos(){

        return logRepository.findAll()
                .stream()
                .map(log -> modelMapper.map(log, Long.class))
                .collect(Collectors.toList());
    }

     public Log obterPorId(Long id) {
        Optional<Log> optLog= logRepository.findById(id);

        if (optLog.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontardo para o id: " + id);
        }
        return modelMapper.map(optLog.get(), Log.class);
    }

}
