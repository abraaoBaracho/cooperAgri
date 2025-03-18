package br.com.cooperagri.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cooperagri.model.DadosBancarios;
import br.com.cooperagri.model.Endereco;
import br.com.cooperagri.model.Fornecedor;
import br.com.cooperagri.model.Usuario;
import br.com.cooperagri.model.enums.BancoCode;
import br.com.cooperagri.repositories.FornecedorRepository;
import br.com.cooperagri.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private FornecedorRepository fornecedorRepository;

    //@Autowired
    //private FuncionarioRepository funcionarioRepository;


    @Override
    public void run(String... args) throws Exception {
        
       Usuario u1 = new Usuario(null,"abraao","123","abraao@gmail.com");
       Usuario u2 = new Usuario(null,"ismael","12345","ismael@gmail.com");
       Usuario u3 = new Usuario(null,"evelin","123456","evelin@gmail.com");

        usuarioRepository.saveAll(Arrays.asList(u1,u2,u3));

        Endereco e1 = new Endereco(null, "181", "534350-424", "61", "caetes 1", "Abreu e Lima");

        Endereco e2 = new Endereco(null, "181", "546546546", "56", "caetes 1", "abreu e lima");

        DadosBancarios d1 = new DadosBancarios( "1606", "01666-6", "corrente",
         "81992528738", BancoCode.BRASIL);

         DadosBancarios d2 = new DadosBancarios( "1606", "01616-6", "corrente",
         "81992528738", BancoCode.BRADESCO);

        Fornecedor fo1 = new Fornecedor(null, "amos", "123-456-789-00", "1123456",
         e1, "983534766", d1 , u1, "?????", "mano");
       
         fornecedorRepository.save(fo1);

        // Funcionario fu1 = new Funcionario(null, "abraao", "123456798", "5456465", e2, "85465465", u1);
         
        // funcionarioRepository.save(fu1);
         
          
    }

}
