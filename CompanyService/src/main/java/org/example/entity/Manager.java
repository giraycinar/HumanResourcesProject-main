package org.example.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "tbl_managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max=20,message = "Kullanici adi en az 3, en fazla 20 karakter olabilir.")
    private String name;
    @Size(min = 2, max=20,message = "Kullanici adi en az 3, en fazla 20 karakter olabilir.")
    private String surname;
    @Column(unique = true)
    @Email
    private String email;
    @Size(min=11,max = 11, message = "Telefon numarasi 11 karakterli olmalidir.")
    private String phone;

    private Long companyId;
    private String position;
    private Long createAt;
    private Long updateAt;
    private Boolean isActive;

}
