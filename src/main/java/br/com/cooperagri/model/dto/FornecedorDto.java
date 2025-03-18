
package br.com.cooperagri.model.dto;

import br.com.cooperagri.model.DadosBancarios;
import br.com.cooperagri.model.Endereco;
import br.com.cooperagri.model.Fornecedor;
import br.com.cooperagri.model.Usuario;

public record FornecedorDto(
        Long id,
        String nome,
        String cpf,
        String rg,
        String telefone,
        Endereco endereco,
        DadosBancarios dadosBancarios,
        String caf_dap,
        String apelido,
        Long userId) {

    public FornecedorDto(Fornecedor modelo){
        this(modelo.getId(),
             modelo.getNome(),
             modelo.getCpf(),
             modelo.getRg(),
             modelo.getTelefone(),
             modelo.getEndereco(),
             modelo.getDados_bancarios(),
             modelo.getCaf_dap(),
             modelo.getApelido(),
             modelo.getUsuario().getId());
             System.out.println(modelo);
    }

    public Fornecedor modelo(Usuario usuario){
        Fornecedor novoFornecedor = new Fornecedor();

        novoFornecedor.setNome(nome);
        novoFornecedor.setCpf(cpf);
        novoFornecedor.setRg(rg);
        novoFornecedor.setTelefone(telefone);
        novoFornecedor.setEndereco(endereco);
        novoFornecedor.setDados_bancarios(dadosBancarios);
        novoFornecedor.setCaf_dap(caf_dap);
        novoFornecedor.setApelido(apelido);
        novoFornecedor.setUsuario(usuario);

        return novoFornecedor;
    }
}