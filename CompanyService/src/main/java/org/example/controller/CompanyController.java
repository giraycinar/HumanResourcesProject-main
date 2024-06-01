package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.CompanyAddRequestDto;
import org.example.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.constants.RestApiUrls.COMPANY;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/create-manager")
    public ResponseEntity<Void> createManager(@RequestBody CompanyAddRequestDto dto) {
        companyService.createManager(dto);
        return ResponseEntity.ok().build();
    }


}
