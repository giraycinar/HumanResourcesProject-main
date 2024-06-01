package org.example.service;

import org.example.dto.request.EmployeeAddRequestDto;
import org.example.dto.request.EmployeeManagerAddRequestDto;
import org.example.entity.Employee;
import org.example.entity.EmployeeManager;
import org.example.exception.EmployeeServiceException;
import org.example.exception.ErrorType;
import org.example.mapper.EmployeeManagerMapper;
import org.example.repository.EmployeeManagerRepository;
import org.example.utility.enums.ERole;
import org.example.utility.enums.EStatus;
import org.springframework.stereotype.Service;
import org.example.utility.ServiceManager;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeManagerService extends ServiceManager<EmployeeManager, Long> {
    private final EmployeeManagerRepository employeeManagerRepository;

    public EmployeeManagerService( EmployeeManagerRepository employeeManagerRepository) {
        super(employeeManagerRepository);
        this.employeeManagerRepository = employeeManagerRepository;
    }

    public void createEmployee(EmployeeManagerAddRequestDto dto) {
        EmployeeManager employee = new EmployeeManager();
        employee.setNameSurname(dto.getNameSurname());
        employee.setEmail(dto.getEmail());
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        employeeManagerRepository.save(employee);
    }
    public boolean deleteEmployee(EmployeeManagerAddRequestDto dto) {
        try {
            employeeManagerRepository.delete(EmployeeManagerMapper.INSTANCE.fromCompanyAddRequestDtoToCompany(dto));
            return true;
        } catch (Exception e) {
            throw new EmployeeServiceException(ErrorType.INTERNAL_ERROR);
        }
    }
    public Boolean activateStatus(String email) {
        Optional<EmployeeManager> optionalEmployeeManager = employeeManagerRepository.findByEmail(email);
        if(optionalEmployeeManager.isPresent()){
            optionalEmployeeManager.get().setStatus(EStatus.ACTIVE);
            update(optionalEmployeeManager.get());
            return true;
        } else {
            throw new EmployeeServiceException(ErrorType.ERROR_EMPLOYEE_NOT_FOUND);
        }
    }
    public Boolean inactiveStatus(String email) {
        Optional<EmployeeManager> optionalEmployeeManager = employeeManagerRepository.findByEmail(email);
        if(optionalEmployeeManager.isPresent()){
            optionalEmployeeManager.get().setStatus(EStatus.INACTIVE);
            update(optionalEmployeeManager.get());
            return true;
        } else {
            throw new EmployeeServiceException(ErrorType.ERROR_EMPLOYEE_NOT_FOUND);
        }
    }

}
