package org.example.manager;

import org.example.dto.response.RegisterResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.example.constants.RestApiUrls.*;
@FeignClient(url = "https://localhost:7071/api/v1/user-profile", name = "auth-userprofile")

public interface AuthManager {

    @PostMapping("/create")
    public ResponseEntity<Boolean> registerUser(@RequestBody RegisterResponseDto dto);
    @GetMapping(ACTIVATE_STATUS+"/{authId}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable("authId") Long authId);
    @DeleteMapping(DELETE_BY_TOKEN)
    public ResponseEntity<Boolean> deleteByToken(@RequestParam("token") String token);
}
