package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.DebitAddRequestDto;
import org.example.entity.Debit;
import org.example.exception.EmployeeServiceException;
import org.example.exception.ErrorType;
import org.example.repository.DebitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DebitService {
    private final DebitRepository debitRepository;

    public void save(DebitAddRequestDto dto) {
        debitRepository.save(Debit.builder()
                .employeeId(dto.getEmployeeId())
                .name(dto.getName())
                .information(dto.getInformation())
                .dateOfIssue(System.currentTimeMillis())
                .build());
    }


    public Debit getById(Long id) {

        Optional<Debit> debit = debitRepository.findById(id);

        Optional<Debit> debit = debitRepository.findOptionalById(id);

        if (debit.isEmpty()) {
           throw new EmployeeServiceException(ErrorType.ERROR_DEBIT_NOT_FOUND);
        }
        return debit.get();
    }

    public Iterable<Debit> getAll() {
       List<Debit> debitList = debitRepository.findAll();
       if (debitList.isEmpty()) {
           throw new EmployeeServiceException(ErrorType.ERROR_DEBIT_NOT_FOUND);
       }
       return debitList;
    }

    public List<Debit> getAllByEmployeeId(Long employeeId) {
        List<Debit> debitList = debitRepository.findAllByEmployeeId(employeeId);
        if (debitList.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.ERROR_DEBIT_NOT_FOUND);
        }
        return debitList;
    }
}
