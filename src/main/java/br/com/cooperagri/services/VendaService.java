package br.com.cooperagri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Venda;
import br.com.cooperagri.repositories.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

   public List<Venda> findAll(){
    return vendaRepository.findAll();
   }

   public Venda findById(Long id){
    var vendaLocalizado = vendaRepository.findById(id);
    return vendaLocalizado.get();

   }
    
}
