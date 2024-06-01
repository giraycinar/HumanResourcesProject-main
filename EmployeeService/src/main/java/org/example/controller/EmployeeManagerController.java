package org.example.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.EmployeeAddRequestDto;
import org.example.dto.request.EmployeeManagerAddRequestDto;
import org.example.dto.request.EmployeeUpdateRequestDto;
import org.example.dto.response.BaseResponse;
import org.example.entity.Employee;
import org.example.service.EmployeeManagerService;
import org.example.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.example.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEEMANAGER)
public class EmployeeManagerController {
    private final EmployeeManagerService employeeManagerService;
    @PostMapping("/create-employee")
    @Transactional
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<String>> add(@RequestBody EmployeeManagerAddRequestDto dto){
        employeeManagerService.createEmployee(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .status(200)
                .message("Kayıt Başarılı")
                .build());
    }

    @GetMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@PathVariable("email") String email){
        return ResponseEntity.ok(employeeManagerService.activateStatus(email));
    }

    @GetMapping(INACTIVE_STATUS)
    public ResponseEntity<Boolean> rejectStatus(@PathVariable("email") String email){
        return ResponseEntity.ok(employeeManagerService.inactiveStatus(email));
    }


}
