package br.com.cooperagri.model.dto;

import br.com.cooperagri.model.Produto;
import br.com.cooperagri.model.Usuario;

import java.math.BigDecimal;

public record ProdutoDto(
        Long id,
        String nome,
        String ncm,
        Long codigo,
        BigDecimal valor,
        Integer quantidade,
        String unidadeDeMedida,
        Long userId) {

    public ProdutoDto(Produto modelo) {
        this(
            modelo.getId(),
            modelo.getNome(),
            modelo.getNcm(),
            modelo.getCodigo(),
            modelo.getValor() != null ? new BigDecimal(modelo.getValor()) : null,
            modelo.getQuantidade(),
            modelo.getUnidade_de_medida(),
            modelo.getUsuario() != null ? modelo.getUsuario().getId() : null
        );
    }

    public Produto modelo(Usuario usuario) {
        Produto novoProduto = new Produto();

        novoProduto.setId(id);
        novoProduto.setNome(nome);
        novoProduto.setNcm(ncm);
        novoProduto.setCodigo(codigo);
        novoProduto.setValor(valor != null ? valor.toString() : null);
        novoProduto.setQuantidade(quantidade);
        novoProduto.setUnidade_de_medida(unidadeDeMedida);
        novoProduto.setUsuario(usuario);

        return novoProduto;
    }
}
