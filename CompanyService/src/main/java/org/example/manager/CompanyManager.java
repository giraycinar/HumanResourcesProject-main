package org.example.manager;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "http://localhost:7070/api/v1/auth",name = "company-auth")

public interface CompanyManager {

}
