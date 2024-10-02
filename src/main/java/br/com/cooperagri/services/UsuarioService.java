package br.com.cooperagri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cooperagri.model.Usuario;
import br.com.cooperagri.repositories.UsuarioRepository;
import br.com.cooperagri.services.exceptions.ResouceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        var aux = usuarioRepository.findById(id);
        return aux.orElseThrow(()-> new ResouceNotFoundException(id));
    }

    @Transactional
    public Usuario create(Usuario user) {
        return usuarioRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            
          throw new ResouceNotFoundException(id);
        }
    }

    @Transactional
    public Usuario update(Long id, Usuario user) {
        try {
            Usuario aux = usuarioRepository.getReferenceById(id);
            updateData(aux, user);
            return usuarioRepository.save(aux);
        } catch (EntityNotFoundException e) {
            throw new ResouceNotFoundException(id);
        }
    }

    private void updateData(Usuario aux, Usuario user) {
       
        Optional.ofNullable(user.getEmail()).ifPresent(aux::setEmail);
        Optional.ofNullable(user.getNome()).ifPresent(aux::setNome);
        Optional.ofNullable(user.getSenha()).ifPresent(aux::setSenha);
    }
}
