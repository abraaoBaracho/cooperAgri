package br.com.cooperagri.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cooperagri.model.Usuario;
import br.com.cooperagri.model.dto.AutenticarLogin;
import br.com.cooperagri.services.AuthService;
import br.com.cooperagri.services.UsuarioService;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = usuarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Usuario user = usuarioService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value="/cadastrar")
    public ResponseEntity<Usuario> create(@RequestBody Usuario newUser) {
        Usuario user = usuarioService.create(newUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticarLogin login) {
        
        return ResponseEntity.ok(authService.login(login));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario newUser) {
        newUser = usuarioService.update(id, newUser);

        return ResponseEntity.ok().body(newUser);
    }
}
