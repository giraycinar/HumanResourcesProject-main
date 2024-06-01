package org.example.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.EmployeeAddRequestDto;
import org.example.dto.request.EmployeeUpdateRequestDto;
import org.example.dto.response.BaseResponse;
import org.example.entity.Employee;
import org.example.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(ADD)
    @Transactional
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<String>> add(@RequestBody EmployeeAddRequestDto dto){
        employeeService.add(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .status(200)
                .message("Kayıt Başarılı")
                .build());
    }

    @GetMapping(GETBYID)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<Employee>> getById(@PathVariable("id") Long id){
       return ResponseEntity.ok(BaseResponse.<Employee>builder()
               .status(200)
               .data(employeeService.getById(id))
               .build());
    }



    @GetMapping(GETALLBYCOMPANYID)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<List<Employee>>> getAllByCompanyId(@PathVariable("companyId") Long companyId){
        return ResponseEntity.ok(BaseResponse.<List<Employee>>builder()
                .status(200)
                .data(employeeService.getAllByCompanyId(companyId))
                .build());
    }


    @PostMapping(UPDATE)
    @Transactional
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<String>> update(@RequestBody EmployeeUpdateRequestDto dto){
        employeeService.update(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .status(200)
                .message("Güncelleme Basarılı")
                .build());
    }
}
