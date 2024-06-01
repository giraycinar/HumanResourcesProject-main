package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.EmployeeAddRequestDto;
import org.example.dto.request.EmployeeUpdateRequestDto;
import org.example.entity.Employee;
import org.example.exception.EmployeeServiceException;
import org.example.exception.ErrorType;
import org.example.repository.EmployeeRepository;
import org.example.utility.enums.ERole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void add(EmployeeAddRequestDto dto) {
        Employee employee = new Employee();
        employee.setCompanyId(dto.getCompanyId());
        employee.setTCNo(dto.getTCNo());
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setMaritalStatus(dto.getMaritalStatus());
        employee.setGender(dto.getGender());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setRole(ERole.EMPLOYEE);
        employeeRepository.save(employee);
    }


    public Employee getById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()){
            throw new EmployeeServiceException(ErrorType.ERROR_EMPLOYEE_NOT_FOUND);
        }
        return employee.get();
    }



    public List<Employee> getAllByCompanyId(Long companyId) {
        List<Employee> employees = employeeRepository.findAllByCompanyId(companyId);
        if (employees.isEmpty()){
            throw new EmployeeServiceException(ErrorType.ERROR_EMPLOYEE_NOT_FOUND);
        }
        return employees;
    }


    public void update(EmployeeUpdateRequestDto dto) {
        Employee employee=employeeRepository.findById(dto.getId()).orElseThrow(()->new EmployeeServiceException(ErrorType.ERROR_EMPLOYEE_NOT_FOUND));
        employee.setCompanyId(dto.getCompanyId());
        employee.setTCNo(dto.getTCNo());
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setMaritalStatus(dto.getMaritalStatus());
        employee.setGender(dto.getGender());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employeeRepository.save(employee);
    }
}

