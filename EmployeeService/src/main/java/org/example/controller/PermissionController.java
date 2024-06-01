package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.dto.request.DebitAddRequestDto;
import org.example.dto.request.PermissionRequestAddDto;
import org.example.dto.response.BaseResponse;
import org.example.entity.Permission;
import org.example.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PERMISSION)
public class PermissionController {
    private final PermissionService permissionService;


    @PostMapping(ADD)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<String>> add(@RequestBody PermissionRequestAddDto dto){
        permissionService.save(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .status(200)
                .message("Kayıt Başarılı")
                .build());
    }

    @GetMapping(GETALLBYEMPLOYEEID)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponse<List<Permission>>> getAllByEmployeeId(@PathVariable("id") Long id){
        return ResponseEntity.ok(BaseResponse.<List<Permission>>builder()
                .status(200)
                .data(permissionService.getAllByEmployeeId(id))
                .build());
    }

}
