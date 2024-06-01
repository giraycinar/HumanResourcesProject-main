package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.ActivateStatusRequestDto;
import org.example.dto.request.LoginRequestDto;
import org.example.dto.request.RegisterRequestDto;
import org.example.dto.response.BaseResponse;
import org.example.dto.response.LoginResponseDto;
import org.example.dto.response.RegisterResponseDto;
import org.example.service.AuthService;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import static org.example.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)

public class AuthController {
    private final AuthService authService;
  //  private final JwtTokenManager jwtTokenManager;


    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<LoginResponseDto>  login(@RequestBody LoginRequestDto dto) {

        return ResponseEntity.ok(authService.login(dto));

    }




    @PostMapping(REGISTER)
    @CrossOrigin("*")
   public ResponseEntity<BaseResponse<String>> register(@RequestBody RegisterRequestDto dto) {
        authService.save(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                        .status(200)
                        .message("Kayıt Başarılı")

                .build());

    }


    @PostMapping(REGISTER_WITH_RABBITMQ)
    public ResponseEntity<RegisterResponseDto>  registerWithRabbitMq (@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.registerWithRabbitMQ(dto));
    }
    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateStatusRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }
//    @PostMapping(MANAGERLOGIN)
//    public ResponseEntity<String> managerLogin(@RequestBody LoginRequestDto dto){
//        return ResponseEntity.ok(authService.login(dto));
//    }
//    @PostMapping(EMPLOYEELOGIN)
//    public ResponseEntity<String> employeeLogin(@RequestBody LoginRequestDto dto){
//        return ResponseEntity.ok(authService.login(dto));
//    }
//    @PostMapping(ADMINLOGIN)
//    public ResponseEntity<String> adminLogin(@RequestBody LoginRequestDto dto){
//        return ResponseEntity.ok(authService.login(dto));
//    }
}
