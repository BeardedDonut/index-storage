package com.example.indexstorage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/heartbeat")
public class Heartbeat {

    @GetMapping()
    public ResponseEntity getServerHeartbeat(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
