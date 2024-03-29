package com.afulvio.booklify.userservice.service;

import com.afulvio.booklify.userservice.dto.UserDto;
import com.afulvio.booklify.userservice.entity.User;
import com.afulvio.booklify.userservice.mapper.UserMapper;
import com.afulvio.booklify.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    public UserDto findUserByEmail(String email) {
        log.info("Start finding a user");
        Optional<User> opt = userRepository.findByEmail(email);
        if (opt.isPresent()) {
            log.info("User founded");
            return UserMapper.MAPPER.mapUserToUserDto(opt.get());
        }
        log.info("User not founded");
        return UserDto.builder().build();
    }

    public List<UserDto> findAllUsers() {
        log.info("Start finding all user");
        return userRepository.findAll().stream()
                .map(UserMapper.MAPPER::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto saveUser(UserDto userDto) {
        log.info("Start adding a user");
        User user = UserMapper.MAPPER.mapUserDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        log.info("User added");
        return UserMapper.MAPPER.mapUserToUserDto(savedUser);
    }

    @Autowired
    public  void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
