package br.com.cooperagri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cooperagri.model.DadosBancarios;

public interface DadosBancariosRepository extends JpaRepository<DadosBancarios, Long> {

}
