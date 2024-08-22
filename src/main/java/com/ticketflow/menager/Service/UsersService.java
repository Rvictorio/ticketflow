package com.ticketflow.menager.Service;

import com.ticketflow.menager.Dto.UsersDTO;
import com.ticketflow.menager.Entity.Users;
import com.ticketflow.menager.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public UsersDTO updateUser(Long id, UsersDTO userDTO) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setRole(userDTO.role());

        Users updatedUser = usersRepository.save(user);

        return new UsersDTO(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getPassword(),
                updatedUser.getRole()
        );
    }

    public void deleteUser(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        usersRepository.delete(user);
    }

    public UsersDTO createUser(UsersDTO userDTO) {
        Users user = new Users(
                null,
                userDTO.name(),
                userDTO.email(),
                userDTO.password(),
                userDTO.role()
        );

        Users savedUser = usersRepository.save(user);

        return new UsersDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getRole()
        );
    }

    public List<UsersDTO> getAllUsers() {
        List<Users> usersList = usersRepository.findAll();

        return usersList.stream()
                .map(user -> new UsersDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }
}
