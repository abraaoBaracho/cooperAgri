package br.com.cooperagri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Fornecedor;
import br.com.cooperagri.repositories.FornecedorRepository;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

   public List<Fornecedor> findAll(){
    return fornecedorRepository.findAll();
   }

   public Fornecedor findById(Long id){
    var fornecedorLocalizado = fornecedorRepository.findById(id);
    return fornecedorLocalizado.get();

   }
    
}
