package org.example.mapper;

import org.example.dto.request.EmployeeManagerAddRequestDto;
import org.example.entity.EmployeeManager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeManagerMapper {
    EmployeeManagerMapper INSTANCE = Mappers.getMapper(EmployeeManagerMapper.class);

    EmployeeManager fromCompanyAddRequestDtoToCompany(final EmployeeManagerAddRequestDto dto);

}
