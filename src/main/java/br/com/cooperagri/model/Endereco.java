package br.com.cooperagri.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String rua;
    private String cep;
    private String numero;
    private String bairro;
    private String cidade;
    
    public Endereco(Long id, String rua, String cep, String numero, String bairro, String cidade) {
        this.id = id;
        this.rua = rua;
        this.cep = cep.replaceAll("[^0-9]", "");
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public void setCep(String cep){
        this.cep = cep.replaceAll("[^0-9]", "");
    }
}
