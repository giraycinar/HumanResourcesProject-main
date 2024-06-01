package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.utility.enums.EStatus;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_employee_manager")
public class EmployeeManager extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSurname;
    @Email
    private String email;
    private Long employeeId;
    private Long salary;
    private Long prim;
    private Long expense;
    private int dayOff;
    private String position;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    //private String debit; //zimmet
    // private Double shift; //vardiya
    // private Double  mola; // daha sonra bakılacak

}
