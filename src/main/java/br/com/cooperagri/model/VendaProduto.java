package br.com.cooperagri.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cooperagri.model.pk.VendaProdutoPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "venda_produto")
public class VendaProduto implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private VendaProdutoPk id = new VendaProdutoPk();
    private Integer quantidade;

    public VendaProduto(Venda venda, Produto produto, Integer quantidade) {
        id.setProduto(produto);
        id.setVenda(venda);
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @JsonIgnore
    public Venda getVenda() {
        return id.getVenda();
    }

    public void setVenda(Venda venda) {
        id.setVenda(venda);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        VendaProduto other = (VendaProduto) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
