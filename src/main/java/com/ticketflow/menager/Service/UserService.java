package com.ticketflow.menager.Service;

import com.ticketflow.menager.Dto.UsersDTO;
import com.ticketflow.menager.Entity.Users;
import com.ticketflow.menager.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository; // Injeção do repositório

    public UsersDTO getUserById(Long id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        Users user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        return new UsersDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }


}
