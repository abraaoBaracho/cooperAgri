package br.com.cooperagri.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Funcionario extends Pessoa {

    public Funcionario(Long id, String nome, String cpf, String rg, Endereco endereco, String telefone,
            DadosBancarios dados_bancarios) {
        super(id, nome, cpf, rg, endereco, telefone, dados_bancarios);
        
    }

}
