package com.afulvio.booklify.userservice.dto.response;

import com.afulvio.booklify.userservice.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse {

    private List<UserDTO> users;

}
