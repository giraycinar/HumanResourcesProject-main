package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
}
