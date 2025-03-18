package br.com.cooperagri.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cooperagri.model.enums.BancoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DadosBancarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    @JsonIgnore
    private Integer banco;
    @Column(nullable = false, length = 20)
    private String agencia;
    @Column(nullable = false, length = 20)
    private String conta;
    @Column(nullable = false, length = 20)
    private String tipo_conta;
    @Column(nullable = false, length = 40)
    private String pix;

    public DadosBancarios(String agencia, String conta, String tipo_conta, String pix, BancoCode banco) {
        this.agencia = agencia;
        this.conta = conta;
        this.tipo_conta = tipo_conta;
        this.pix = pix;
        this.setBancoCode(banco);
    }

    public BancoCode getBancoCode() {
        return BancoCode.valueOf(banco);
    }

    public final void setBancoCode(BancoCode banco) {
        if (banco != null) {
            this.banco = banco.getCode();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((conta == null) ? 0 : conta.hashCode());
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
        DadosBancarios other = (DadosBancarios) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (conta == null) {
            if (other.conta != null) {
                return false;
            }
        } else if (!conta.equals(other.conta)) {
            return false;
        }
        return true;
    }

}
