package com.ticketflow.menager.Controller;

import com.ticketflow.menager.Dto.UsersDTO;
import com.ticketflow.menager.Service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Endpoint para buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id) {
        UsersDTO user = usersService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Endpoint para listar todos os usuários
    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<UsersDTO> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint para criar um novo usuário
    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO userDTO) {
        UsersDTO newUser = usersService.createUser(userDTO);
        return ResponseEntity.ok(newUser);
    }


    // Endpoint para atualizar um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable Long id, @RequestBody UsersDTO userDTO) {
        UsersDTO updatedUser = usersService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint para deletar um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
