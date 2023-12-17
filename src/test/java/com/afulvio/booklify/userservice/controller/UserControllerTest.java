package com.afulvio.booklify.userservice.controller;

import com.afulvio.booklify.userservice.dto.UserDto;
import com.afulvio.booklify.userservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController testSubject;

    @Mock
    private UserService userService;

    @Test
    void getUserByEmailTest() {
        // given
        UserDto userDto = UserDto.builder().build();
        when(userService.findUserByEmail(anyString())).thenReturn(userDto);

        // when
        ResponseEntity<UserDto> out = testSubject.getUserByEmail("afulvio@booklify.com");

        // then
        Assertions.assertNotNull(out.getBody());
    }

    @Test
    void saveUser() {
        // given
        when(userService.saveUser(any())).thenReturn(UserDto.builder().build());

        // when
        ResponseEntity<UserDto> out = testSubject.saveUser(UserDto.builder().build());

        // then
        Assertions.assertNotNull(out.getBody());
    }
}
