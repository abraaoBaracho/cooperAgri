package br.com.cooperagri.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String nome;
    private String ncm;
    private Long codigo;
    private BigDecimal preco;
    private Integer quantidade;
    private String unidade_de_medida;

    public Produto() {
    }

    public Produto(Long id, String nome, String ncm, Long codigo, String preco, Integer quantidade,
            String unidade_de_medida) {
        this.id = id;
        this.nome = nome;
        this.ncm = ncm;
        this.codigo = codigo;
        this.preco = formatDecimal(preco);
        this.quantidade = quantidade;
        this.unidade_de_medida = unidade_de_medida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getPreco() {
        return preco.toString();
    }

    public void setPreco(String preco) {
        this.preco = formatDecimal(preco);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade_de_medida() {
        return unidade_de_medida;
    }

    public void setUnidade_de_medida(String unidade_de_medida) {
        this.unidade_de_medida = unidade_de_medida;
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
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    private BigDecimal formatDecimal(String preco) {
        var aux = new BigDecimal(preco);
        return aux.setScale(2, RoundingMode.HALF_UP);
    }

}
