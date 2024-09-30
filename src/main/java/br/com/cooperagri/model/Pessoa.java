package br.com.cooperagri.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String rg;

    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dados_bancarios_id")
    private DadosBancarios dados_bancarios;

    public Pessoa(Long id, String nome, String cpf, String rg, Endereco endereco, String telefone,
            DadosBancarios dados_bancarios) {
        this.id = id;
        this.nome = nome;
        this.cpf = formatarString(cpf);
        this.rg = formatarString(rg);
        this.telefone = formatarString(telefone);
        this.endereco = endereco;
        this.dados_bancarios = dados_bancarios;
    }

    public void setCpf(String cpf) {
        this.cpf = formatarString(cpf);
    }

    public void setRg(String rg) {
        this.rg = formatarString(rg);
    }

    public void setTelefone(String telefone) {
        this.telefone = formatarString(telefone);
    }

    private String formatarString(String string) {
        return string.replaceAll("[^0-9]", "");

    }

}
