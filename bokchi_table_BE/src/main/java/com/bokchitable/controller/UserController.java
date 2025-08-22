package com.bokchitable.controller;

import com.bokchitable.dto.request.UserRequest;
import com.bokchitable.dto.response.UserResponse;
import com.bokchitable.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){ this.userService = userService; }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody UserRequest req){
        UserResponse res = userService.signup(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}