package br.com.cooperagri.model;

import br.com.cooperagri.model.pk.RecebimentoProdutoPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "compra")
public class Compra {

    @EmbeddedId
    private RecebimentoProdutoPk id;
    private Integer quantidade;

    public Compra(Produto produto, Recebimento recebimento, Integer quantidade) {

        id.setProduto(produto);
        id.setRecebimento(recebimento);
        this.quantidade = quantidade;

    }

    public void setProduto(Produto produto){
        id.setProduto(produto);
    }

    public Produto getProduto(){
        return id.getProduto();
    }

    public void setRecebimento(Recebimento recebimento){
        id.setRecebimento(recebimento);
    }

    public Recebimento getRecebimento(){
        return id.getRecebimento();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
