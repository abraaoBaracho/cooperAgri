package br.com.cooperagri.model;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recibo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Produto produto;

    private BigDecimal valor;

    private String valorExtenso;

    private Fornecedor fornecedor;

    private Date dia;

}
