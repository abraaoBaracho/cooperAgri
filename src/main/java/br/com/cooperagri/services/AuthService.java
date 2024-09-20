package br.com.cooperagri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Acesso;
import br.com.cooperagri.model.AutenticarLogin;
import br.com.cooperagri.security.jwt.JwtUtil;
import br.com.cooperagri.services.impl.UserDetailsImpl;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public Acesso login(AutenticarLogin auth) {

        try {
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(auth.getCpf(), auth.getSenha());

            Authentication authentication = authenticationManager.authenticate(userAuth);

            UserDetailsImpl usuarioAuth = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtil.gerarTokenComUserDetailsImpl(usuarioAuth);

            Acesso acesso = new Acesso(token);
            return acesso;
        } catch (BadCredentialsException e) {
            //TODO usuario ou senha invalidos
        }
        return new Acesso("Acesso negado");
    }
}
