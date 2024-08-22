package com.ticketflow.menager.Dto;

import com.ticketflow.menager.enums.Priority;
import com.ticketflow.menager.enums.Status;

import java.time.LocalDateTime;

public record TicketDTO(
        Long id,
        String title,
        String description,
        Status status,
        Priority priority,
        String creatorEmail,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
