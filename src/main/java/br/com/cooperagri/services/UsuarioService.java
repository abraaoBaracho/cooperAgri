package br.com.cooperagri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Usuario update(Long id, Usuario user) {
        Usuario aux = usuarioRepository.getReferenceById(id);
        updateData(aux, user);
        return usuarioRepository.save(aux);
    }

    private void updateData(Usuario aux, Usuario user) {
        Optional.ofNullable(user.getCpf()).ifPresent(aux::setCpf);
        Optional.ofNullable(user.getEmail()).ifPresent(aux::setEmail);
        Optional.ofNullable(user.getNome()).ifPresent(aux::setNome);
        Optional.ofNullable(user.getSenha()).ifPresent(aux::setSenha);
    }

    /*
    public Usuario login(String cpf, String senha) {
        Usuario exemploUsuario = new Usuario();
        exemploUsuario.setCpf(cpf);
        exemploUsuario.setSenha(senha);
        
        // Criar um Example a partir do exemploUsuario
        Example<Usuario> example = Example.of(exemploUsuario);
        
        // Usar findBy com um Example e uma queryFunction para encontrar o usuário
        //return usuarioRepository.findBy(example, query -> query.first()).orElse(null);
        return usuarioRepository.findOne(example).orElse(null);
    }
     */
}
