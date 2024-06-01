package org.example.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class LoginRequestDto {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

}
