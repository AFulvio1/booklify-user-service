package com.afulvio.booklify.userservice.controller;

import com.afulvio.booklify.userservice.util.JwtUtil;
import com.afulvio.booklify.userservice.dto.request.AuthRequest;
import com.afulvio.booklify.userservice.dto.request.SaveUserRequest;
import com.afulvio.booklify.userservice.dto.request.UpdateUserRequest;
import com.afulvio.booklify.userservice.dto.response.*;
import com.afulvio.booklify.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
@Tag(
        name = "Users APIs",
        description = "Create Users, Update Users, Get Users, Delete Users"
)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "Log in")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid AuthRequest authRequest,
            UriComponentsBuilder uriBuilder
    ) {
        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        AuthResponse response = new AuthResponse(jwtUtil.generateToken(userDetails));
        URI location = uriBuilder.path("/api/users/save")
                .buildAndExpand(response.getToken())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/get/{email}")
    @Operation(summary = "Get User by email")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    public ResponseEntity<GetUserResponse> getByEmail(
            @PathVariable("email")
            @Valid @Email(message = "Email should be valid")
            String email
    ){
        GetUserResponse response = userService.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    public ResponseEntity<GetUsersResponse> getAll(){
        GetUsersResponse response = userService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    @Operation(summary = "Create an User")
    @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
    public ResponseEntity<SaveUserResponse> save(
            @RequestBody SaveUserRequest request,
            UriComponentsBuilder uriBuilder
    ){
        SaveUserResponse response = userService.save(request);
        URI location = uriBuilder.path("/api/users/save")
                .buildAndExpand(response.getUser().getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete User by ID")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    public ResponseEntity<DeleteUserResponse> delete(
            @PathVariable("id") @Valid final Long id
    ) {
        DeleteUserResponse response = userService.deleteById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update an User")
    @ApiResponse(responseCode = "202", description = "HTTP Status 202 OK")
    public ResponseEntity<UpdateUserResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ){
        UpdateUserResponse response = userService.update(id, request);
        return ResponseEntity.accepted().body(response);
    }


}
