package com.example.nto.controller;

import com.example.nto.entity.Code;
import com.example.nto.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @PatchMapping("/api/{login}/open")
    public Code update(@PathVariable String login, @RequestBody Code newCode) {
        return codeService.update(login, newCode);
    }
}
