package com.afulvio.booklify.userservice.service;

import com.afulvio.booklify.userservice.dto.UserDto;
import com.afulvio.booklify.userservice.entity.User;
import com.afulvio.booklify.userservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService testSubject;

    @Mock
    private UserRepository userRepository;

    @Test
    void findUserByEmail() {
        // given
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(User.builder().build()));

        // when
        UserDto userDto = testSubject.findUserByEmail("afulvio@booklify.com");

        // then
        Assertions.assertNotNull(userDto);
    }

}
