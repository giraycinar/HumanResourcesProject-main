package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.DebitAddRequestDto;
import org.example.dto.response.BaseResponse;
import org.example.entity.Debit;
import org.example.entity.Expense;
import org.example.service.DebitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(DEBIT)
public class DebitController {
    private final DebitService debitService;

    @PostMapping(ADD)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<String>> add(@RequestBody DebitAddRequestDto dto){
        debitService.save(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .status(200)
                .message("Kayıt Başarılı")
                .build());
    }
    @GetMapping(GETBYID)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<Debit>> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(BaseResponse.<Debit>builder()
                .data(debitService.getById(id))
                .status(200)
                .build());
    }
    @GetMapping(GETALL)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<Iterable<Debit>>> getAll(){
        return ResponseEntity.ok(BaseResponse.<Iterable<Debit>>builder()
                .status(200)
                .data(debitService.getAll())
                .build());
    }

    @GetMapping(GETALLBYEMPLOYEEID)
    @CrossOrigin("*")
    public ResponseEntity<List<Debit>> getAllByEmployeeId(@PathVariable("id") Long employeeId){
        return ResponseEntity.ok(debitService.getAllByEmployeeId(employeeId));
    }
}
