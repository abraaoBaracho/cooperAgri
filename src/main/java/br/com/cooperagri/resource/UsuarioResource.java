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
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthService authService;

    @Operation(summary = "Retorna todos os usuarios", description = "Retorna todos os usuarios salvos no banco")
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = usuarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Localiza um usuario pelo id", description = "Retorna um usuario localizado pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Usuario user = usuarioService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Salva usuario", description = "Salva um usuario no banco e o retorna")
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Usuario> create(@RequestBody Usuario newUser) {
        Usuario user = usuarioService.create(newUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @Operation(summary = "Realiza o login", description = "Autentica um usuario pelo cpf e senha e retorna o token e o id")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticarLogin login) {

        return ResponseEntity.ok(authService.login(login));
    }

    @Operation(summary = "Deletar usuario", description = "Delete o usuario do id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Edita um usuario", description = "Edita um usuario do id informado com os dados fornecido")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario newUser) {
        newUser = usuarioService.update(id, newUser);

        return ResponseEntity.ok().body(newUser);
    }
}
