package br.com.cooperagri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Usuario;
import br.com.cooperagri.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        var aux = usuarioRepository.findById(id);
        return aux.get();
    }

    public Usuario create(Usuario user) {
        return usuarioRepository.save(user);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

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
