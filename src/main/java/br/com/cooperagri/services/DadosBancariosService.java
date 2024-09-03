package br.com.cooperagri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.DadosBancarios;
import br.com.cooperagri.repositories.DadosBancariosRepository;

@Service
public class DadosBancariosService {

    @Autowired
    private DadosBancariosRepository dados_bancariosRepository;

   public List<DadosBancarios> findAll(){
    return dados_bancariosRepository.findAll();
   }

   public DadosBancarios findById(Long id){
    var dados_bancariosLocalizado = dados_bancariosRepository.findById(id);
    return dados_bancariosLocalizado.get();

   }
    
}
