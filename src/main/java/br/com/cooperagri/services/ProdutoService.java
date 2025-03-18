package br.com.cooperagri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Produto;
import br.com.cooperagri.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

   public List<Produto> findAll(){
    return produtoRepository.findAll();
   }

   public Produto findById(Long id){
    var produtoLocalizado = produtoRepository.findById(id);
    return produtoLocalizado.get();

   }

   public Produto create(Produto produto) {
        return produtoRepository.save(produto);
    }
    
}
