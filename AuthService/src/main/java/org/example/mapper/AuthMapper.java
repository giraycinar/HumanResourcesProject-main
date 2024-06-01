package org.example.mapper;

import org.example.dto.request.RegisterRequestDto;
import org.example.dto.response.RegisterResponseDto;
import org.example.entity.Auth;
import org.example.rabbitmq.model.RegisterModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromAuthRequestDto(final RegisterRequestDto dto);

    RegisterResponseDto fromAuthToCreateUserRequestDto(final Auth auth);

    Auth fromRegisterRequestToAuth(final RegisterRequestDto dto);

    RegisterResponseDto fromAuthToRegisterResponseDto(Auth auth);
    @Mapping(source = "id", target ="authId")
    RegisterModel fromAuthToRegisterModel(Auth auth);
}