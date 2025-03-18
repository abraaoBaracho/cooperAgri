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
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
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
    private Set<Fornecedor> fornecedor = new HashSet<>();
     

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Produto() {
    }

    public Produto(Long id, String nome, String ncm, Long codigo, String valor, Integer quantidade,
            String unidade_de_medida, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.ncm = ncm;
        this.codigo = codigo;
        this.valor = formatDecimal(valor);
        this.quantidade = quantidade;
        this.unidade_de_medida = unidade_de_medida;
        this.usuario = usuario;
    }

    public String getValor() {
        return valor.toString();
    }

    public void setValor(String valor) {
        this.valor = formatDecimal(valor);
    }

    private BigDecimal formatDecimal(String valor) {
        var aux = new BigDecimal(valor);
        return aux.setScale(2, RoundingMode.HALF_UP);
    }

}
