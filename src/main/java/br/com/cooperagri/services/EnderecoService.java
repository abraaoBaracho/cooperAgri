package br.com.cooperagri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Endereco;
import br.com.cooperagri.repositories.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

   public List<Endereco> findAll(){
    return enderecoRepository.findAll();
   }

   public Endereco findById(Long id){
    var enderecoLocalizado = enderecoRepository.findById(id);
    return enderecoLocalizado.get();

   }
    
}
