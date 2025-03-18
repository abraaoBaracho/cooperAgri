package br.com.cooperagri.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperagri.model.Produto;
import br.com.cooperagri.model.Usuario;
import br.com.cooperagri.model.dto.ProdutoDto;
import br.com.cooperagri.services.ProdutoService;
import br.com.cooperagri.services.UsuarioService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResouce {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ProdutoDto> criar(@RequestBody ProdutoDto produtoDto) {
        Usuario usuario = usuarioService.findById(produtoDto.userId());

        Produto produto = produtoDto.modelo(usuario);
        Produto produtoSalvo = produtoService.create(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutoDto(produtoSalvo));
    }
}
