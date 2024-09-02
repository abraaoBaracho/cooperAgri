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
public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String nome;
    private String tipo;
    private BigDecimal valor_servico;
    private String quantidade_de_horas;
    private BigDecimal valor_total;

    public Servico() {
    }
    

    public Servico(Long id, String nome, String tipo, String valor_servico, String quantidade_de_horas,
            BigDecimal valor_total) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor_servico = formatDecimal(valor_servico);
        this.quantidade_de_horas = quantidade_de_horas;
        this.valor_total = valor_total;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(String valor_servico) {
        this.valor_servico = formatDecimal(valor_servico);
    }

    public String getQuantidade_de_horas() {
        return quantidade_de_horas;
    }

    public void setQuantidade_de_horas(String quantidade_de_horas) {
        this.quantidade_de_horas = quantidade_de_horas;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Servico other = (Servico) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    private BigDecimal formatDecimal(String valor_servico) {
        var aux = new BigDecimal(valor_servico);
        return aux.setScale(2, RoundingMode.HALF_UP);
    }
    

    

}
