package br.com.cooperagri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cooperagri.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
