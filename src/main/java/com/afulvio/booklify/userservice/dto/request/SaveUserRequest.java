package com.afulvio.booklify.userservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {

    @NotEmpty(message = "User firstname should not be null or empty")
    @Schema(description = "User First Name")
    private String firstname;

    @NotEmpty(message = "User lastname should not be null or empty")
    @Schema(description = "User Last Name")
    private String lastname;

    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email address should be valid")
    @Schema(description = "User Email Address")
    private String email;

    private String role;

    private String password;

}
