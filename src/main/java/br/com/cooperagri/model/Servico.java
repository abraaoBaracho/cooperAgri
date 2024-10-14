package br.com.cooperagri.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import br.com.cooperagri.model.enums.ServicoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @ManyToMany
    @JoinTable(
            name = "servico_trabalhador",
            joinColumns = @JoinColumn(name = "servico_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private Set<Funcionario> funcionarios = new HashSet<>();

    public Servico(Set<Funcionario> funcionarios, ServicoCode servico_code, String valor_servico,
            Integer quantidade_de_horas) {

        this.funcionarios = funcionarios;
        setServicoCode(servico_code);
        this.valorServico = formatDecimal(valor_servico);
        this.quantidadeDeHoras = quantidade_de_horas;
        this.dia = formatarData();
        this.valorTotal = calcularValorTotal();
    }

    public void addFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
    }

    public Set<Long> getFuncionariosId(){
        Set<Long> ids = new HashSet<>();
        for (Funcionario funcionario : funcionarios) {
            ids.add(funcionario.getId());
        }

        return ids;
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

    public String getValorServico() {
        return valorServico.toString();
    }

    public void setValorTotal() {
        this.valorTotal = calcularValorTotal();
    }

    public String getValorTotal() {
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
