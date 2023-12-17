package it.afulvio.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.afulvio.userservice.dto.UserDto;
import it.afulvio.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(
        name = "Users APIs",
        description = "Create Users, Update Users, Get Users, Delete Users"
)
public class UserController {

    private UserService userService;

    @GetMapping("/get-user/{email}")
    @Operation(summary = "Get User by email")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    public ResponseEntity<UserDto> getUserByEmail(
            @PathVariable("email") String email
    ){
        UserDto userDto = userService.findUserByEmail(email);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    @Operation(summary = "Create an User")
    @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
    public ResponseEntity<UserDto> saveUser(
            @RequestBody UserDto userDto
    ){
        UserDto savedUser = userService.saveUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
