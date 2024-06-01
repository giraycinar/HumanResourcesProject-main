package org.example.repository;

import org.example.entity.Debit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DebitRepository extends JpaRepository<Debit, Long> {

    Optional<Debit> findById(Long id);


    List<Debit> findAllByEmployeeId(Long employeeId);

    Optional<Debit> findOptionalById(Long id);
}
