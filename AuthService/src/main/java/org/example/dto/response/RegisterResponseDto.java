package org.example.dto.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class RegisterResponseDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String activationCode;
}
