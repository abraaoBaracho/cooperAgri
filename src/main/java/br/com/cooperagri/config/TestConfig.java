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

    @Override
    public void run(String... args) throws Exception {
        
       Usuario u1 = new Usuario(null,"abraao","111-111-654-79","123","abraao@qwe.com");
       Usuario u2 = new Usuario(null,"ismael","331.111.123-79","12345","ismael@qwe.com");
       Usuario u3 = new Usuario(null,"evelin","111/111a888.79","123456","evelin@qwe.com");

        usuarioRepository.saveAll(Arrays.asList(u1,u2,u3));

        Endereco e1 = new Endereco(null, "181", "534350-424", "61", "caetes 1", "Abreu e Lima");

        DadosBancarios d1 = new DadosBancarios(null, "1606", "01666-6", "corrente",
         "81992528738", BancoCode.BRADESCO);

        Fornecedor f1 = new Fornecedor(null, "amos", "123-456-789-00", "1123456",
         e1, "983534766", d1, "?????", "mano");
       
         fornecedorRepository.save(f1);

    }

}
