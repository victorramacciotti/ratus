package com.academia.infra.security;

import com.academia.repository.UserRepository;
import com.academia.service.AuthorizationService;
import com.academia.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if(token != null) {
            var login = tokenService.validateToken(token);
            if (login != null) {
                UserDetails user = authorizationService.loadUserByUsername(login);
                if (user != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        // Itera sobre todos os cookies da requisição
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                // Procura pelo cookie com o nome "jwt_token" (o mesmo nome definido no AuthenticationController)
                if ("jwt_token".equals(cookie.getName())) {
                    return cookie.getValue(); // Retorna o valor do token
                }
            }
        }
        return null; // Se o cookie não for encontrado
    }
}
