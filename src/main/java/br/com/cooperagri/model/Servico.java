package br.com.cooperagri.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.cooperagri.model.enums.ServicoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Servico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
    private Integer servico_code;
    private BigDecimal valor_servico;
    private Integer quantidade_de_horas;
    private String dia;

    public Servico(Funcionario funcionario, ServicoCode servico_code, String valor_servico,
            Integer quantidade_de_horas) {

        this.funcionario = funcionario;
        setServicoCode(servico_code);
        this.valor_servico = formatDecimal(valor_servico);
        this.quantidade_de_horas = quantidade_de_horas;
        this.dia = formatarData();
    }

    private String formatarData() {
        LocalDateTime now = LocalDateTime.now();

        // Definir o formato desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Formatar o LocalDateTime para uma string
        String formattedDateTime = now.format(formatter);

        return formattedDateTime;
    }

    private void setServicoCode(ServicoCode servico_code) {
        if (servico_code != null) {
            this.servico_code = servico_code.getCode();
        }
    }

    public void setValor_servico(String valor_servico) {
        this.valor_servico = formatDecimal(valor_servico);
    }

    private BigDecimal formatDecimal(String valor_servico) {
        var aux = new BigDecimal(valor_servico);
        return aux.setScale(2, RoundingMode.HALF_UP);
    }

}
