package com.academia.controller;

import com.academia.entity.User.AuthenticationDTO;
import com.academia.entity.User.RegisterDTO;
import com.academia.entity.User.User;
import com.academia.infra.security.TokenService;
import com.academia.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        ResponseCookie cookie = ResponseCookie.from("jwt_token", token) // "jwt_token" é o nome do cookie
                .httpOnly(true) // Impede que JavaScript acesse o cookie (segurança XSS)
                .secure(false) // Mude para 'true' em produção (requer HTTPS)
                .path("/") // O cookie será enviado para todos os caminhos da sua aplicação
                .maxAge(60 * 60 * 24) // Tempo de vida do cookie (ex: 24 horas)
                .sameSite("Lax") // Proteção contra CSRF. "Lax" é um bom padrão para a maioria dos casos.
                .build();

        // 2. Retorna a resposta com o cookie no cabeçalho Set-Cookie
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Login bem-sucedido!");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (data.password() == null || data.password().isBlank()) return ResponseEntity.badRequest().body("Senha não pode ser nula ou vazia.");

        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().body("O usuário já existe");

        String encryptPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.login(), encryptPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        ResponseCookie cookie = ResponseCookie.from("jwt_token", "")
                .httpOnly(true)
                .secure(false)  // em produção, use true com HTTPS
                .path("/")
                .maxAge(0)  // indica para o navegador apagar o cookie
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logout realizado com sucesso!");
    }
}
