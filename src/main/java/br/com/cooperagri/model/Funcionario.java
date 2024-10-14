package br.com.cooperagri.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Funcionario extends Pessoa {

    @ManyToMany(mappedBy = "funcionarios")
    private Set<Servico> servicos = new HashSet<>();

    public Funcionario(Long id, String nome, String cpf, String rg, Endereco endereco, String telefone,
            DadosBancarios dados_bancarios) {
        super(id, nome, cpf, rg, endereco, telefone, dados_bancarios);
        
    }

}
