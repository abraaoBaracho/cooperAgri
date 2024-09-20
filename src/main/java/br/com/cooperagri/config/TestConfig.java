package br.com.cooperagri.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cooperagri.model.Usuario;
import br.com.cooperagri.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        
       Usuario u1 = new Usuario(null,"abraao","111-111-654-79","123","abraao@qwe.com");
       Usuario u2 = new Usuario(null,"ismael","331.111.123-79","12345","ismael@qwe.com");
       Usuario u3 = new Usuario(null,"evelin","111/111a888.79","123456","evelin@qwe.com");

        usuarioRepository.saveAll(Arrays.asList(u1,u2,u3));
    }

}
