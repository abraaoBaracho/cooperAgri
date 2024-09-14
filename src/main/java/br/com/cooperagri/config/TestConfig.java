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
        
       Usuario u1 = new Usuario(null,"abraao","111111","123","abraao@qwe.com");
       Usuario u2 = new Usuario(null,"ismael","331111","12345","ismael@qwe.com");

        usuarioRepository.saveAll(Arrays.asList(u1,u2));
    }

}
