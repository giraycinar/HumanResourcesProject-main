package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.CompanyAddRequestDto;
import org.example.entity.Company;
import org.example.exception.CompanyServiceException;
import org.example.exception.ErrorType;
import org.example.mapper.CompanyMapper;
import org.example.repository.CompanyRepository;
import org.example.utility.enums.EStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.lettuce.core.ShutdownArgs.Builder.save;
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    public void save(Company company) {
        try {
            companyRepository.save(company);
        } catch (Exception e) {
            throw new CompanyServiceException(ErrorType.INTERNAL_ERROR);
        }
    }
    public boolean createManager(CompanyAddRequestDto dto) {
        try {
            companyRepository.saveAll(List.of());
            save(CompanyMapper.INSTANCE.fromCompanyAddRequestDtoToCompany(dto));
            return true;

        } catch (Exception e) {
            throw new CompanyServiceException(ErrorType.INTERNAL_ERROR);
        }


    }

}
