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

import br.com.cooperagri.model.Fornecedor;
import br.com.cooperagri.model.enums.BancoCode;
import br.com.cooperagri.services.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/fornecedor")
@CrossOrigin
public class FornecedorResource {

    @Autowired
    private FornecedorService fornecedorService;

    @Operation(summary = "Retorna todos os fornecedores", description = "Retorna todos os fornecedores salvos no banco")
    @GetMapping
    public ResponseEntity<List<Fornecedor>> findAll() {
        List<Fornecedor> list = fornecedorService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Localiza um fornecedor pelo id", description = "Retorna um fornecedor localizado pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.findById(id);
        return ResponseEntity.ok().body(fornecedor);
    }

    @Operation(summary = "Salva fornecedor", description = "Salva um fornecedor no banco e o retorna")
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Fornecedor> create(@RequestBody Fornecedor newfFornecedor) {
        Fornecedor fornecedor = fornecedorService.create(newfFornecedor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fornecedor.getId()).toUri();

        return ResponseEntity.created(uri).body(fornecedor);
    }

    @Operation(summary = "Deletar fornecedor", description = "Delete o fornecedor do id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fornecedorService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Edita um fornecedor", description = "Edita um fornecedor do id informado com os dados fornecido")
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> update(@PathVariable Long id, @RequestBody Fornecedor newFornecedor) {
        newFornecedor = fornecedorService.update(id, newFornecedor);

        return ResponseEntity.ok().body(newFornecedor);
    }

    @Operation(summary = " Retorna os bancos", description = "Retorna todos os bancos salvos na enum em um array")
    @GetMapping("bancos")
    public ResponseEntity<BancoCode[]> getBancosCode() {
        BancoCode[] codes = BancoCode.getCodes();

        return ResponseEntity.ok().body(codes);

    }
}
