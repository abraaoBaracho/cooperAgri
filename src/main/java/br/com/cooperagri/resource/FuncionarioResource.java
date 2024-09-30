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

import br.com.cooperagri.model.Funcionario;
import br.com.cooperagri.services.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/funcionario")
@CrossOrigin
public class FuncionarioResource {

    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(summary = "Retorna todos os funcionarios", description = "Retorna todos os funcionarios salvos no banco")
    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> list = funcionarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Localiza um funcionario pelo id", description = "Retorna um funcionario localizado pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    @Operation(summary = "Salva funcionario", description = "Salva um funcionario no banco e o retorna")
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario newfFuncionario) {
        Funcionario funcionario = funcionarioService.create(newfFuncionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).body(funcionario);
    }

    @Operation(summary = "Deletar funcionario", description = "Delete o funcionario do id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Edita um funcionario", description = "Edita um funcionario do id informado com os dados fornecido")
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario newFuncionario) {
        newFuncionario = funcionarioService.update(id, newFuncionario);

        return ResponseEntity.ok().body(newFuncionario);
    }
}
