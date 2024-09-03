package br.com.cooperagri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Servico;
import br.com.cooperagri.repositories.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

   public List<Servico> findAll(){
    return servicoRepository.findAll();
   }

   public Servico findById(Long id){
    var servicoLocalizado = servicoRepository.findById(id);
    return servicoLocalizado.get();

   }
    
}
