package br.com.cooperagri.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Fornecedor extends Pessoa {
    private static final long serialVersionUID = 1L;

    private String caf_dap;
    private String apelido;

}
