package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.supplier.Supplier;
import com.pdvsystem.api.domain.user.RegisterDTO;
import com.pdvsystem.api.domain.user.User;
import com.pdvsystem.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*
     * Busca todos os usuários
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /*
     * Edita Usuario
     */
    public User updateUser(String id, RegisterDTO data) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum usuario encontrado"));

        user.setRole(data.role());
        user.setName(data.name());
        user.setEmail(data.email());

        return userRepository.save(user);
    }

    /*
     * Deleta Usuario
     */
    public void deleteUser(String id) {
        User supplier = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum usuario encontrado"));
        userRepository.delete(supplier);
    }

}
