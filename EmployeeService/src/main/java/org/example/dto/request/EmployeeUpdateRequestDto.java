package org.example.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeUpdateRequestDto {
    private Long id;
    private Long companyId;
    @Size(min=11,max = 11, message = "TC numarası 11 karakterli olmalidir.")
    private Long TCNo;
    @Size(min = 2, max=20,message = "Kullanici adi en az 3, en fazla 20 karakter olabilir.")
    private String name;
    @Size(min = 2, max=20,message = "Kullanici adi en az 3, en fazla 20 karakter olabilir.")
    private String surname;
    @Email
    private String email;
    @Size(min=11,max = 11, message = "Telefon numarasi 11 karakterli olmalidir.")
    private String phone;
    private String maritalStatus; // medeni hali
    private String gender;
    private Long dateOfBirth;
}
