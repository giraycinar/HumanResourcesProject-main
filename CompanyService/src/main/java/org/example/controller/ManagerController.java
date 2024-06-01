package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.constants.RestApiUrls.MANAGER;

@RestController
@RequiredArgsConstructor
@RequestMapping(MANAGER)
public class ManagerController {
}
