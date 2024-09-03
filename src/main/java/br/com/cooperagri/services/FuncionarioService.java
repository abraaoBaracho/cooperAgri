package br.com.cooperagri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Funcionario;
import br.com.cooperagri.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

   public List<Funcionario> findAll(){
    return funcionarioRepository.findAll();
   }

   public Funcionario findById(Long id){
    var funcionarioLocalizado = funcionarioRepository.findById(id);
    return funcionarioLocalizado.get();

   }
    
}
