package br.com.cooperagri.resource;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperagri.model.Usuario;
import br.com.cooperagri.services.UsuarioService;

@RestController
@RequestMapping(value ="/users")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> list = usuarioService.findAll();
        return ResponseEntity.ok(list);
    }
}
