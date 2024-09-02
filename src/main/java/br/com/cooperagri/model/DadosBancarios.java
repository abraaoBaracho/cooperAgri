package br.com.cooperagri.model;

import java.io.Serializable;

import br.com.cooperagri.model.enums.BancoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DadosBancarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Integer banco;
    private String agencia;
    private String conta;
    private String tipo_conta;
    private String pix;

    public DadosBancarios() {
    }

    public DadosBancarios(Long id, String agencia, String conta, String tipo_conta, String pix, BancoCode banco) {
        this.id = id;
        this.agencia = agencia;
        this.conta = conta;
        this.tipo_conta = tipo_conta;
        this.pix = pix;
        this.setBancoCode(banco);
    }

    public BancoCode getBancoCode() {
        return BancoCode.valueOf(banco);
    }

    public void setBancoCode(BancoCode banco) {
        if (banco != null) {
            this.banco = banco.getCode();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    public void setTipo_conta(String tipo_conta) {
        this.tipo_conta = tipo_conta;
    }

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
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
        DadosBancarios other = (DadosBancarios) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
