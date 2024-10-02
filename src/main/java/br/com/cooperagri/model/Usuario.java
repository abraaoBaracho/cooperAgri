package br.com.cooperagri.model;

import java.io.Serializable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(nullable = false, length = 50)
    private String nome;
    @Column(nullable = false)
    private String senha;
    @Column(unique = true, nullable = false, length = 30)
    private String email;

    public Usuario(Long id, String nome, String senha, String email) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        setSenha(senha);  // Use o método para hash da senha
    }

    public void setSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.senha = encoder.encode(senha);  // Hash da senha
    }

}
