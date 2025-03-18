package br.com.cooperagri.model.dto;

import br.com.cooperagri.model.Servico;
import br.com.cooperagri.model.enums.ServicoCode;

public record ServicoDto(

        Long id,
        //Set<Long> funcionarioId,
        ServicoCode servicoCode,
        String valorServico,
        Integer quantidadeDeHoras,
        String valorTotal,
        String dia) {

    public ServicoDto(Servico modelo) {
        this(modelo.getId(),
               // modelo.getFuncionariosId(),
                modelo.getServicoCode(),
                modelo.getValorServico(),
                modelo.getQuantidadeDeHoras(),
                modelo.getValorTotal(),
                modelo.getDia());
    }

    public Servico modelo() {
        Servico novoServico = new Servico();

        //novoServico.setFuncionarios(funcionario);
        novoServico.setServicoCode(servicoCode);
        novoServico.setValorServico(valorServico);
        novoServico.setQuantidadeDeHoras(quantidadeDeHoras);
        novoServico.setValorTotal();
        novoServico.setDia();

        return novoServico;
    }

}
