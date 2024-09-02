package br.com.cooperagri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cooperagri.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
