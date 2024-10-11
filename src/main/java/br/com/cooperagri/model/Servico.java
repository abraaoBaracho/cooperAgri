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
    @Column(nullable = false, length = 50)
    private Integer servico_code;
    @Column(nullable = false, length = 50)
    private BigDecimal valor_servico;
    @Column(length = 50)
    private Integer quantidade_de_horas;
    @Column(length = 50)
    private BigDecimal valor_total;
    @Column(nullable = false, length = 50)
    private String dia;

    public Servico(Funcionario funcionario, ServicoCode servico_code, String valor_servico,
            Integer quantidade_de_horas) {

        this.funcionario = funcionario;
        setServicoCode(servico_code);
        this.valor_servico = formatDecimal(valor_servico);
        this.quantidade_de_horas = quantidade_de_horas;
        this.dia = formatarData();
        this.valor_total = calcularValorTotal();
    }

    private void setServicoCode(ServicoCode servico_code) {
        if (servico_code != null) {
            this.servico_code = servico_code.getCode();
        }
    }

    public void setValor_servico(String valor_servico) {
        this.valor_servico = formatDecimal(valor_servico);
    }

    public String getValor_servico() {
        return valor_servico.toString();
    }

    public void setValor_total() {
        this.valor_total = calcularValorTotal();
    }

    public String getValor_total() {
        return valor_total.toString();
    }

    private BigDecimal calcularValorTotal() {
        var soma = valor_servico.multiply(BigDecimal.valueOf(quantidade_de_horas));
        return soma;
    }

    private String formatarData() {
        LocalDateTime now = LocalDateTime.now();

        // Definir o formato desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Formatar o LocalDateTime para uma string
        String formattedDateTime = now.format(formatter);

        return formattedDateTime;
    }

    private BigDecimal formatDecimal(String valor_servico) {
        var aux = new BigDecimal(valor_servico);
        return aux.setScale(2, RoundingMode.HALF_UP);
    }

}
