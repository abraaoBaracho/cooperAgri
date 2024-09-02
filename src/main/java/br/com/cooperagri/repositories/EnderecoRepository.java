package br.com.cooperagri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cooperagri.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
