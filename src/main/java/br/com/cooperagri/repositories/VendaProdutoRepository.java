package br.com.cooperagri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cooperagri.model.VendaProduto;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, Long> {

}
