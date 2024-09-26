package br.com.cooperagri.resource.exceptions;

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

import br.com.cooperagri.model.Funcionario;
import br.com.cooperagri.services.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionario")
@CrossOrigin
public class FuncionarioResource {

     @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> list = funcionarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    @PostMapping(value="/cadastrar")
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario newfFuncionario) {
        Funcionario funcionario = funcionarioService.create(newfFuncionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).body(funcionario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

     @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario newFuncionario) {
        newFuncionario = funcionarioService.update(id, newFuncionario);

        return ResponseEntity.ok().body(newFuncionario);
    }
}
