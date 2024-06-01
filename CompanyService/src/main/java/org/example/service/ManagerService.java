package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.CompanyAddRequestDto;
import org.example.entity.Company;
import org.example.entity.Manager;
import org.example.exception.CompanyServiceException;
import org.example.exception.ErrorType;
import org.example.mapper.CompanyMapper;
import org.example.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;


}
