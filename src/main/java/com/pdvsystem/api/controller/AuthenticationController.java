package com.pdvsystem.api.controller;

import com.pdvsystem.api.domain.user.AuthenticationDTO;
import com.pdvsystem.api.domain.user.LoginResponseDTO;
import com.pdvsystem.api.domain.user.RegisterDTO;
import com.pdvsystem.api.domain.user.User;
import com.pdvsystem.api.infra.security.TokenService;
import com.pdvsystem.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private com.pdvsystem.api.repositories.CompanyRepository companyRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO body) {
        var usernamepassword = new UsernamePasswordAuthenticationToken(body.email(), body.password());
        var auth = authenticationManager.authenticate(usernamepassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO( token, user.getEmail(),user.getName(), user.getId(), user.getRole().toString(), user.getCompanyId()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO body) {
        if (this.userRepository.findByEmail(body.email()) != null) {
            return  ResponseEntity.badRequest().body("Email já cadastrado");
        }

        // validate company and company password
        var company = companyRepository.findById(body.companyId()).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        boolean ok = new BCryptPasswordEncoder().matches(body.companyPassword(), company.getPassword());
        if (!ok) {
            return ResponseEntity.status(403).body("Senha da empresa inválida");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(body.password());
        User newUser = new User(body.email(), encryptedPassword,body.name(), body.role(), body.companyId());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
