package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.ExpenseAddRequestDto;
import org.example.dto.response.BaseResponse;
import org.example.entity.Expense;
import org.example.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EXPENSE)
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping(ADD)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<String>> Register(@RequestBody ExpenseAddRequestDto dto){
        expenseService.add(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .status(200)
                .message("Kayıt Başarılı")
                .build());

    }
    @GetMapping(GETBYID)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<Expense>> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(BaseResponse.<Expense>builder()
                .status(200)
                .data(expenseService.getById(id))
                .build());
    }

    @GetMapping(GETALL)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<Iterable<Expense>>> getAll(){
        return ResponseEntity.ok(BaseResponse.<Iterable<Expense>>builder()
                .status(200)
                .data(expenseService.getAll())
                .build());
    }
    @GetMapping(GETALLBYEMPLOYEEID)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<Iterable<Expense>>> getAllByEmployeeId(@PathVariable("id") Long employeeId){
        return ResponseEntity.ok(BaseResponse.<Iterable<Expense>>builder()
                .status(200)
                .data(expenseService.getAllByEmployeeId(employeeId))
                .build());
    }
}
