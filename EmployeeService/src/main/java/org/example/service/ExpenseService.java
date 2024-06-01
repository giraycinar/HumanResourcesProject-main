package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.ExpenseAddRequestDto;
import org.example.entity.Expense;
import org.example.exception.EmployeeServiceException;
import org.example.exception.ErrorType;
import org.example.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public void add(ExpenseAddRequestDto dto) {
        expenseRepository.save(Expense.builder()
                        .employeeId(dto.getEmployeeId())
                        .expenseType(dto.getExpenseType())
                        .amount(dto.getAmount())
                        .currency(dto.getCurrency())
                        .dateOfApplication(System.currentTimeMillis())
                .build());
    }

    public Expense getById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.ERROR_EXPENSE_NOT_FOUND);
        }
        return expense.get();
    }

    public Iterable<Expense> getAll() {
        List<Expense> expensesList = expenseRepository.findAll();
        if (expensesList.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.ERROR_EXPENSE_NOT_FOUND);
        }
        return expensesList;
    }

    public Iterable<Expense> getAllByEmployeeId(Long employeeId) {
        List<Expense> expensesList = expenseRepository.findAllByEmployeeId(employeeId);
        if (expensesList.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.ERROR_EXPENSE_NOT_FOUND);
        }
        return expensesList;
    }
}
