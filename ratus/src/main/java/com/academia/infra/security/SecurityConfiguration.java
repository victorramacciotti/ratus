package com.academia.infra.security;

import com.academia.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private AuthorizationService authorizationService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Rota do Sagger
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Rotas de Register, Login e Logout
                        .requestMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN") // Bloquear depois
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Todos podem ter acesso ao login
                        .requestMatchers(HttpMethod.POST, "/auth/logout").authenticated() // Ter acesso ou logout somente se tiver login

                        // Rotas Clientes
                        .requestMatchers(HttpMethod.POST, "/clientes").hasAnyRole("ADMIN", "RECEPCIONIST")
                        .requestMatchers(HttpMethod.GET, "/clientes").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/clientes/**").hasAnyRole("ADMIN", "RECEPCIONIST")
                        .requestMatchers(HttpMethod.DELETE, "/clientes").hasRole("ADMIN")

                        // Rotas Instrutores
                        .requestMatchers(HttpMethod.GET, "/instrutores/*").authenticated()
                        .requestMatchers(HttpMethod.POST, "/instrutores").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/instrutores/{id}").hasRole("ADMIN")

                        // Rotas de Exercício
                        .requestMatchers(HttpMethod.POST, "/exercicios").hasAnyRole("ADMIN", "INSTRUCTOR")
                        .requestMatchers(HttpMethod.GET, "/api/exercicios/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/exercicios/**").hasAnyRole("ADMIN", "INSTRUCTOR")
                        .requestMatchers(HttpMethod.DELETE, "/exercicios/**").hasAnyRole("ADMIN")

                        // Rotas de Treino
                        .requestMatchers(HttpMethod.POST, "/api/treinos").hasAnyRole("ADMIN", "INSTRUTOR")
                        .requestMatchers(HttpMethod.GET, "/api/treinos/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/treinos/**").hasAnyRole("ADMIN", "INSTRUTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/treinos/**").hasAnyRole("ADMIN", "INSTRUTOR")

                        // Rotas Execução de Exercício
                        .requestMatchers(HttpMethod.POST, "/execucoes-exercicios").hasAnyRole("ADMIN", "INSTRUTOR")
                        .requestMatchers(HttpMethod.GET, "/execucoes-exercicios/**").hasAnyRole("ADMIN", "INSTRUTOR", "RECEPCIONIST")
                        .requestMatchers(HttpMethod.GET, "/execucoes-exercicios/cliente/**").hasAnyRole("ADMIN", "INSTRUTOR", "RECEPCIONIST")
                        .requestMatchers(HttpMethod.PUT, "/execucoes-exercicios/**").hasAnyRole("ADMIN", "INSTRUTOR")
                        .requestMatchers(HttpMethod.DELETE, "/execucoes-exercicios/**").hasAnyRole("ADMIN")

                        // Rotas Sugestão Treino
                        .requestMatchers(HttpMethod.POST, "/sugestoes-treinos").hasAnyRole("ADMIN", "INSTRUTOR")

                        // Rotas Folha de Pagamento
                        .requestMatchers(HttpMethod.POST, "/api/folhas-pagamento").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/folhas-pagamento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/folhas-pagamento/funcionario/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/folhas-pagamento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/folhas-pagamento/**").hasRole("ADMIN")

                        // Rotas Hello Word
                        .requestMatchers(HttpMethod.GET, "/hello").permitAll()
                        .requestMatchers(HttpMethod.GET, "/hello/user").authenticated()
                        .requestMatchers(HttpMethod.GET, "/hello/admin").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(authorizationService) // <- ESSENCIAL!
                .cors(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permita os domínios do seu frontend.
        // Em desenvolvimento, geralmente é "http://localhost:PORTA_DO_FRONTEND"
        // Em produção, será o domínio real do seu frontend.
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080")); // Exemplo: ajuste para a porta do seu frontend
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos HTTP permitidos
        configuration.setAllowedHeaders(Arrays.asList("*")); // Permite todos os cabeçalhos
        configuration.setAllowCredentials(true); // *** MUITO IMPORTANTE: Permite que o navegador envie cookies/credenciais ***
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica esta configuração a todos os caminhos
        return source;
    }
}
