package org.example.mapper;

import org.example.dto.request.CompanyAddRequestDto;
import org.example.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company fromCompanyAddRequestDtoToCompany(final CompanyAddRequestDto dto);

}
