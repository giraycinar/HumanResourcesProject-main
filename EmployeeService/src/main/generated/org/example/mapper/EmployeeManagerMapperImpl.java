package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.request.EmployeeManagerAddRequestDto;
import org.example.entity.EmployeeManager;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-17T03:55:50+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class EmployeeManagerMapperImpl implements EmployeeManagerMapper {

    @Override
    public EmployeeManager fromCompanyAddRequestDtoToCompany(EmployeeManagerAddRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        EmployeeManager.EmployeeManagerBuilder<?, ?> employeeManager = EmployeeManager.builder();

        employeeManager.salary( dto.getSalary() );
        employeeManager.position( dto.getPosition() );

        return employeeManager.build();
    }
}
