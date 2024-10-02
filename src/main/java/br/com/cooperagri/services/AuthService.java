package br.com.cooperagri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.dto.AutenticarLogin;
import br.com.cooperagri.model.dto.LoginResponse;
import br.com.cooperagri.security.jwt.JwtUtil;
import br.com.cooperagri.services.impl.UserDetailsImpl;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(AutenticarLogin auth) {

        try {
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getSenha());

            Authentication authentication = authenticationManager.authenticate(userAuth);

            UserDetailsImpl usuarioAuth = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtil.gerarTokenComUserDetailsImpl(usuarioAuth);

            Long userId = usuarioAuth.getId();

            return new LoginResponse(token, userId);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciais inválidas");
        }
    }
}
