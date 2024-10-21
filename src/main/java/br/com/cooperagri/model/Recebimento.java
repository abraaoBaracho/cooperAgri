package br.com.cooperagri.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Recebimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Date dia_venda;

    private BigDecimal valor;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    private Boolean foiPago;

    @OneToMany(mappedBy = "id.recebimento")
    private Set<Compra> compras = new HashSet<>();

    public Recebimento(Long id, Date dia_venda, String valor, Fornecedor fornecedor) {
        this.id = id;
        this.dia_venda = dia_venda;
        this.valor = formatDecimal(valor);
        this.fornecedor = fornecedor;
        this.foiPago = false;
    }

    private BigDecimal formatDecimal(String valor) {
        var aux = new BigDecimal(valor);
        return aux.setScale(2, RoundingMode.HALF_UP);
    }

}
