package br.com.cooperagri.model.dto;

import br.com.cooperagri.model.Funcionario;
import br.com.cooperagri.model.Servico;
import br.com.cooperagri.model.enums.ServicoCode;

public record ServicoDto(
        Long funcionarioId,
        ServicoCode servicoCode,
        String valorServico,
        Integer quantidadeDeHoras,
        String valorTotal,
        String dia) {

    public ServicoDto(Servico modelo) {
        this(modelo.getFuncionario().getId(),
        modelo.getServicoCode(),
        modelo.getValorservico(),
        modelo.getQuantidadeDeHoras(),
        modelo.getValortotal(),
        modelo.getDia());
    }

    public Servico modelo(Funcionario funcionario) {
        Servico novoServico = new Servico();

        novoServico.setFuncionario(funcionario);
        novoServico.setServicoCode(servicoCode);
        novoServico.setValorServico(valorServico);
        novoServico.setQuantidadeDeHoras(quantidadeDeHoras);
        novoServico.setValortotal();
        novoServico.setDia();

        return novoServico;
    }

}
