package br.com.cooperagri.model;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Fornecedor extends Pessoa {
    

    private String caf_dap;
    private String apelido;

    @ManyToMany(mappedBy = "funcionarios")
    private Set<Produto> produtos = new HashSet<>();
   
    public Fornecedor(Long id, String nome, String cpf, String rg, Endereco endereco, String telefone,
            DadosBancarios dados_bancarios, String caf_dap, String apelido) {
        super(id, nome, cpf, rg, endereco, telefone, dados_bancarios);
        this.caf_dap = caf_dap;
        this.apelido = apelido;
    }

}
