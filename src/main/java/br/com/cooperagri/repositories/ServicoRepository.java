package br.com.cooperagri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cooperagri.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
