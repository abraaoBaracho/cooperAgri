package br.com.cooperagri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Usuario;
import br.com.cooperagri.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(String cpf, String senha) {
        Usuario exemploUsuario = new Usuario();
        exemploUsuario.setCpf(cpf);
        exemploUsuario.setSenha(senha);

        // Criar um Example a partir do exemploUsuario
        Example<Usuario> example = Example.of(exemploUsuario);

        // Usar findBy com um Example e uma queryFunction para encontrar o usuário
        return usuarioRepository.findBy(example, query -> query.first()).orElse(null);
    }
    
}
