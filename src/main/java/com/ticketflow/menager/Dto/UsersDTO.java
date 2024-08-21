package com.ticketflow.menager.Dto;

import com.ticketflow.menager.enums.Role;

public record UsersDTO(Long id,
                       String name,
                       String email,
                       String password,
                       Role role) {

}
