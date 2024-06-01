package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseAddRequestDto {
    private Long employeeId;
    private String expenseType;
    private Double amount;
    private String currency;
}
