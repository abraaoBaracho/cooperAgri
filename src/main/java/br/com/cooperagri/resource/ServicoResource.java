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
import br.com.cooperagri.model.Servico;
import br.com.cooperagri.model.dto.ServicoDto;
import br.com.cooperagri.model.enums.ServicoCode;
import br.com.cooperagri.services.FuncionarioService;
import br.com.cooperagri.services.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/servico")
@CrossOrigin
public class ServicoResource {

    @Autowired
    ServicoService servicoService;

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping()
    public ResponseEntity<List<Servico>> findAll() {
        List<Servico> list = servicoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação Concluida"),
        @ApiResponse(responseCode = "404", description = "Serviço não localizado")
    })
    public ResponseEntity<ServicoDto> findById(@PathVariable Long id) {
        Servico servico = servicoService.findById(id);
        return ResponseEntity.ok().body(new ServicoDto(servico));
    }

    @Operation(summary = "Salva Serviço", description = "Salva um Serviço no banco e o retorna")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso"),
        @ApiResponse(responseCode = "422", description = "Dados invalidos")
    })
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ServicoDto> create(@RequestBody ServicoDto newfServico) {
        Funcionario funcionario = funcionarioService.findById(newfServico.funcionarioId());
        Servico servico = servicoService.create(newfServico.modelo(funcionario));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servico.getId()).toUri();

        return ResponseEntity.created(uri).body(new ServicoDto(servico));
    }

    @Operation(summary = "Deletar Serviço", description = "Delete o Serviço do id informado")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "Serviço deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Serviço não localizado")
})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Edita um Serviço", description = "Edita um Serviço do id informado com os dados fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Serviço não localizado"),
        @ApiResponse(responseCode = "422", description = "Dados invalidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody Servico newServico) {
        newServico = servicoService.update(id, newServico);

        return ResponseEntity.ok().body(newServico);
    }

    @Operation(summary = " Retorna os serviços", description = "Retorna todos os serviços salvos na enum em um array")
    @GetMapping("servico")
    public ResponseEntity<ServicoCode[]> getServicoCode() {
        ServicoCode[] codes = ServicoCode.getCodes();

        return ResponseEntity.ok().body(codes);

    }
}
