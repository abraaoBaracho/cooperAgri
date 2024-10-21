package br.com.cooperagri.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

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
    private BigDecimal valor;
    private Integer quantidade;
    private String unidade_de_medida;
     @ManyToMany
    @JoinTable(
            name = "produto_fornecedor",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private Set<Fornecedor> funcionarios = new HashSet<>();

    public Produto() {
    }

    public Produto(Long id, String nome, String ncm, Long codigo, String valor, Integer quantidade,
            String unidade_de_medida) {
        this.id = id;
        this.nome = nome;
        this.ncm = ncm;
        this.codigo = codigo;
        this.valor = formatDecimal(valor);
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

    public String getValor() {
        return valor.toString();
    }

    public void setValor(String valor) {
        this.valor = formatDecimal(valor);
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

    private BigDecimal formatDecimal(String valor) {
        var aux = new BigDecimal(valor);
        return aux.setScale(2, RoundingMode.HALF_UP);
    }

}
