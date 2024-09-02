package br.com.cooperagri.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Date dia_venda;
    private BigDecimal valor;
    private Fornecedor fornecedor;
    private Boolean foiPago;

    public Venda() {
    }

    public Venda(Long id, Date dia_venda, String valor, Fornecedor fornecedor) {
        this.id = id;
        this.dia_venda = dia_venda;
        this.valor = formatDecimal(valor);
        this.fornecedor = fornecedor;
        this.foiPago = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDia_venda() {
        return dia_venda;
    }

    public void setDia_venda(Date dia_venda) {
        this.dia_venda = dia_venda;
    }

    public String getValor() {
        return valor.toString();
    }

    public void setValor(String valor) {
        this.valor = formatDecimal(valor);
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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
        Venda other = (Venda) obj;
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

    public Boolean getFoiPago() {
        return foiPago;
    }

    public void setFoiPago(Boolean foiPago) {
        this.foiPago = foiPago;
    }
    

}
