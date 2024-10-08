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

import br.com.cooperagri.model.Servico;
import br.com.cooperagri.services.ServicoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/servico")
@CrossOrigin
public class ServicoResource {

    @Autowired
    ServicoService servicoService;

    @GetMapping()
    public ResponseEntity<List<Servico>> findAll() {
        List<Servico> list = servicoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id) {
        Servico servico = servicoService.findById(id);
        return ResponseEntity.ok().body(servico);
    }

    @Operation(summary = "Salva servico", description = "Salva um servico no banco e o retorna")
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Servico> create(@RequestBody Servico newfServico) {
        Servico servico = servicoService.create(newfServico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servico.getId()).toUri();

        return ResponseEntity.created(uri).body(servico);
    }

    @Operation(summary = "Deletar usuario", description = "Delete o usuario do id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Edita um usuario", description = "Edita um usuario do id informado com os dados fornecido")
    @PutMapping("/{id}")
    public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody Servico newServico) {
        newServico = servicoService.update(id, newServico);

        return ResponseEntity.ok().body(newServico);
    }
}
