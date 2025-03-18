
package br.com.cooperagri.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import br.com.cooperagri.model.Usuario;
import br.com.cooperagri.model.dto.FornecedorDto;
import br.com.cooperagri.model.enums.BancoCode;
import br.com.cooperagri.services.FornecedorService;
import br.com.cooperagri.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/fornecedor")
@CrossOrigin
public class FornecedorResource {

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Retorna todos os fornecedores", description = "Retorna todos os fornecedores salvos no banco")
    @GetMapping
    public ResponseEntity<List<FornecedorDto>> findAll() {
        List<Fornecedor> list = fornecedorService.findAll();
        var dtos = list.stream().map(FornecedorDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }

    @Operation(summary = "Localiza um fornecedor pelo id", description = "Retorna um fornecedor localizado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação Concluida"),
            @ApiResponse(responseCode = "404", description = "Serviço não localizado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorDto> findById(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.findById(id);
        return ResponseEntity.ok().body(new FornecedorDto(fornecedor));
    }

    @Operation(summary = "Salva fornecedor", description = "Salva um fornecedor no banco e o retorna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados invalidos")
    })
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<FornecedorDto> create(@RequestBody FornecedorDto newFornecedor) {
        Usuario usuario = usuarioService.findById(newFornecedor.userId());
        Fornecedor fornecedor = fornecedorService.create(newFornecedor.modelo(usuario));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fornecedor.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new FornecedorDto(fornecedor));
    }

    @Operation(summary = "Deletar fornecedor", description = "Delete o fornecedor do id informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não localizado")
    })
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
