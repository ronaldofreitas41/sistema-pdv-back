package com.pdvsystem.api.infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(autorize -> autorize

                        //Rotas de Autenticação
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()

                        //Rotas de Conta (Somente para Gerente ou Admin)
                        .requestMatchers(HttpMethod.GET,"/api/count/{id}").hasRole("Menager")
                        .requestMatchers(HttpMethod.GET,"/api/count/recieve").hasRole("Menager")
                        .requestMatchers(HttpMethod.GET,"/api/count/pay").hasRole("Menager")
                        .requestMatchers(HttpMethod.POST,"/api/count/recieve").hasRole("Menager")
                        .requestMatchers(HttpMethod.POST,"/api/count/pay").hasRole("Menager")
                        .requestMatchers(HttpMethod.DELETE,"/api/count/{id}").hasRole("Menager")
                        .requestMatchers(HttpMethod.PUT,"/api/count{id}").hasRole("Menager")

                        //Rotas de Produtos (Somente para Gerentes ou Admin)
                        .requestMatchers(HttpMethod.POST,"/api/product").hasRole("Menager")
                        .requestMatchers(HttpMethod.DELETE,"/api/product/{id}").hasRole("Menager")
                        .requestMatchers(HttpMethod.PUT,"/api/product/{id}").hasRole("Menager")

                        //Rotas de Clientes (Somente para Gerentes ou Adimin)
                        .requestMatchers(HttpMethod.POST,"/api/client").hasRole("Menager")
                        .requestMatchers(HttpMethod.PUT,"/api/client/{id}").hasRole("Menager")
                        .requestMatchers(HttpMethod.DELETE,"/api/client/{id}").hasRole("Menager")

                        //Rotas de Fornecedores (Somente para Gerentes ou Adimin)
                        .requestMatchers(HttpMethod.POST,"/api/suplier").hasRole("Menager")
                        .requestMatchers(HttpMethod.PUT,"/api/suplier/{id}").hasRole("Menager")
                        .requestMatchers(HttpMethod.DELETE,"/api/suplier/{id}").hasRole("Menager")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
