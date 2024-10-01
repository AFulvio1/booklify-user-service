package com.afulvio.booklify.userservice.dto.response;

import com.afulvio.booklify.userservice.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserResponse {

    private UserDTO user;
}
