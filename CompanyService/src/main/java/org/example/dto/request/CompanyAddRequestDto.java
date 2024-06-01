package org.example.dto.request;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CompanyAddRequestDto {

    @NotEmpty
    private String companyName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Enumerated
    private Enum status;

}
