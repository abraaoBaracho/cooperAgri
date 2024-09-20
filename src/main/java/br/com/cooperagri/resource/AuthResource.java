package br.com.cooperagri.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperagri.model.AutenticarLogin;
import br.com.cooperagri.services.AuthService;


@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticarLogin login) {
        
        return ResponseEntity.ok(authService.login(login));
    }
    
}
