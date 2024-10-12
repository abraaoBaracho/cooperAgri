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
    private Integer servicoCode;
    @Column(nullable = false, length = 50)
    private BigDecimal valorServico;
    @Column(length = 50)
    private Integer quantidadeDeHoras;
    @Column(length = 50)
    private BigDecimal valorTotal;
    @Column(nullable = false, length = 50)
    private String dia;

    public Servico(Funcionario funcionario, ServicoCode servico_code, String valor_servico,
            Integer quantidade_de_horas) {

        this.funcionario = funcionario;
        setServicoCode(servico_code);
        this.valorServico = formatDecimal(valor_servico);
        this.quantidadeDeHoras = quantidade_de_horas;
        this.dia = formatarData();
        this.valorTotal = calcularValorTotal();
    }

    public ServicoCode getServicoCode() {
        return ServicoCode.valueOf(servicoCode);
    }

    public void setServicoCode(ServicoCode servicoCode) {
        if (servicoCode != null) {
            this.servicoCode = servicoCode.getCode();
        }
    }

    public void setValorServico(String valorServico) {
        this.valorServico = formatDecimal(valorServico);
    }

    public String getValorservico() {
        return valorServico.toString();
    }

    public void setValortotal() {
        this.valorTotal = calcularValorTotal();
    }

    public String getValortotal() {
        return valorTotal.toString();
    }

    public void setDia() {
        this.dia = formatarData();
    }

    private BigDecimal calcularValorTotal() {
        if (quantidadeDeHoras == 0 || quantidadeDeHoras == null) {
            return valorServico;
        } else {
            var soma = valorServico.multiply(BigDecimal.valueOf(quantidadeDeHoras));
            return soma;
        }
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
