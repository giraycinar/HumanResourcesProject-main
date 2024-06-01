package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.ActivateStatusRequestDto;
import org.example.dto.request.LoginRequestDto;
import org.example.dto.request.RegisterRequestDto;
import org.example.dto.response.LoginResponseDto;
import org.example.dto.response.RegisterResponseDto;
import org.example.entity.Auth;
import org.example.exception.AuthServiceException;
import org.example.exception.ErrorType;
import org.example.manager.AuthManager;
import org.example.mapper.AuthMapper;
import org.example.rabbitmq.model.RegisterMailModel;
import org.example.rabbitmq.producer.RegisterMailProducer;
import org.example.rabbitmq.producer.RegisterProducer;
import org.example.repository.AuthRepository;
import org.example.utility.CodeGenerator;

import org.example.utility.JwtTokenManager;
import org.example.utility.ServiceManager;
import org.example.utility.enums.ERole;
import org.example.utility.enums.EStatus;
import org.springframework.cache.CacheManager;

import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service

public class AuthService extends ServiceManager<Auth, Long> {
    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthManager authManager;
    private final CacheManager cacheManager;
    private final RegisterMailProducer registerMailProducer;
    private final RegisterProducer registerProducer;



    public AuthService(AuthRepository authRepository, JwtTokenManager jwtTokenManager, CacheManager cacheManager, AuthManager authManager, RegisterMailProducer registerMailProducer, RegisterProducer registerProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.authManager = authManager;
        this.jwtTokenManager = jwtTokenManager;
        this.cacheManager = cacheManager;
        this.registerMailProducer = registerMailProducer;
        this.registerProducer = registerProducer;
    }

    public void save(RegisterRequestDto dto) {
        authRepository.save(
                Auth.builder()
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .build()
        );
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(auth.isEmpty()){
            throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);
        }
        if(auth.get().getStatus().equals(EStatus.ACTIVE)){
            String token= jwtTokenManager.createToken(auth.get().getId(),auth.get().getRole())
                    .orElseThrow(()->{throw new AuthServiceException(ErrorType.ERROR_CREATE_TOKEN);});
           return LoginResponseDto.builder().token(token).role(auth.get().getRole().toString()).build();

        }
        else {
            throw new AuthServiceException(ErrorType.ERROR_ACCOUNT_NOT_ACTIVE);
        }

    }




     @Transactional
    public RegisterResponseDto register(RegisterRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterRequestToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        save(auth);
//        try {
        authManager.registerUser(AuthMapper.INSTANCE.fromAuthToCreateUserRequestDto(auth));
        cacheManager.getCache("findByRole").evict(auth.getRole().toString().toUpperCase());
//        } catch (Exception e){
//            e.printStackTrace();
//            delete(auth);
//        }
        return AuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }


    public RegisterResponseDto registerWithRabbitMQ(RegisterRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterRequestToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());


        try {
            save(auth);
            registerProducer.sendNewUser(AuthMapper.INSTANCE.fromAuthToRegisterModel(auth));
            registerMailProducer.sendActivationCode(RegisterMailModel.builder()
                    .email(auth.getEmail())
                    .activationCode(auth.getActivationCode())
                    .build());


            cacheManager.getCache("findByRole").evict(auth.getRole().toString().toUpperCase());
        } catch (Exception e){
            throw new AuthServiceException(ErrorType.USER_NOT_CREATED);
        }
        return AuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }
    public Boolean activateStatus(ActivateStatusRequestDto dto) {
        Optional<Auth> optionalAuth = findById(dto.getAuthId());
        if(optionalAuth.isEmpty()){
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        }
        if(optionalAuth.get().getActivationCode().equals(dto.getActivationCode())){
            optionalAuth.get().setStatus(EStatus.ACTIVE);
            update(optionalAuth.get());
            authManager.activateStatus(optionalAuth.get().getId());
            return true;
        } else {
            throw new AuthServiceException(ErrorType.ACTIVATION_CODE_ERROR);
        }
    }
    public String managerLogin(LoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (auth.isEmpty()) {
            throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);
        }
        Auth authInfo = auth.get();
        if (authInfo.getStatus().equals(EStatus.ACTIVE)) {
            if (authInfo.getRole().equals(ERole.MANAGER)) {
                return jwtTokenManager.createToken(authInfo.getId(), authInfo.getRole())
                        .orElseThrow(() -> new AuthServiceException(ErrorType.ERROR_CREATE_TOKEN));
            } else {
                throw new AuthServiceException(ErrorType.ERROR_UNAUTHORIZED_ACCESS);
            }
        } else {
            throw new AuthServiceException(ErrorType.ERROR_ACCOUNT_NOT_ACTIVE);
        }
    }
    public String adminLogin(LoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (auth.isEmpty()) {
            throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);
        }
        Auth authInfo = auth.get();
        if (authInfo.getStatus().equals(EStatus.ACTIVE)) {
            if (authInfo.getRole().equals(ERole.ADMIN)) {
                return jwtTokenManager.createToken(authInfo.getId(), authInfo.getRole())
                        .orElseThrow(() -> new AuthServiceException(ErrorType.ERROR_CREATE_TOKEN));
            } else {
                throw new AuthServiceException(ErrorType.ERROR_UNAUTHORIZED_ACCESS);
            }
        } else {
            throw new AuthServiceException(ErrorType.ERROR_ACCOUNT_NOT_ACTIVE);
        }
    }
    public String employeeLogin(LoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (auth.isEmpty()) {
            throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);
        }
        Auth authInfo = auth.get();
        if (authInfo.getStatus().equals(EStatus.ACTIVE)) {
            if (authInfo.getRole().equals(ERole.EMPLOYEE)) {
                return jwtTokenManager.createToken(authInfo.getId(), authInfo.getRole())
                        .orElseThrow(() -> new AuthServiceException(ErrorType.ERROR_CREATE_TOKEN));
            } else {
                throw new AuthServiceException(ErrorType.ERROR_UNAUTHORIZED_ACCESS);
            }
        } else {
            throw new AuthServiceException(ErrorType.ERROR_ACCOUNT_NOT_ACTIVE);
        }
    }



}
