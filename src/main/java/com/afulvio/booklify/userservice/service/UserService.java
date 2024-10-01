package com.afulvio.booklify.userservice.service;

import com.afulvio.booklify.userservice.configuration.BooklifyPasswordEncoder;
import com.afulvio.booklify.userservice.dto.UserDTO;
import com.afulvio.booklify.userservice.dto.request.SaveUserRequest;
import com.afulvio.booklify.userservice.dto.request.UpdateUserRequest;
import com.afulvio.booklify.userservice.dto.response.*;
import com.afulvio.booklify.userservice.exception.UserRegisteredException;
import com.afulvio.booklify.userservice.exception.UserNotFoundException;
import com.afulvio.booklify.userservice.exception.UserRegisteringException;
import com.afulvio.booklify.userservice.exception.UserUpdatingException;
import com.afulvio.booklify.userservice.model.LocalError;
import com.afulvio.booklify.userservice.model.entity.UserEntity;
import com.afulvio.booklify.userservice.mapper.UserMapper;
import com.afulvio.booklify.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BooklifyPasswordEncoder booklifyPasswordEncoder;


    @Transactional
    public GetUserResponse findByEmail(String email) {
        log.info("Start searching a user with email: {}", email);
        GetUserResponse response = new GetUserResponse();
        userRepository.findByEmail(email).ifPresentOrElse(
                entity -> response.setUser(userMapper.entityToDTO(entity)),
                () -> { throw new UserNotFoundException(LocalError.E001.getMessage()); }
        );
        return response;
    }

    @Transactional
    public GetUsersResponse findAll() {
        log.info("Start searching all user");
        List<UserDTO> users = userRepository.findAll().stream()
                .map(userMapper::entityToDTO)
                .toList();
        return new GetUsersResponse(users);
    }

    @Transactional
    public SaveUserResponse save(SaveUserRequest request) {
        log.info("Start saving a user");
        SaveUserResponse response = new SaveUserResponse();
        userRepository.findByEmail(request.getEmail()).ifPresentOrElse(
                user -> {
                    throw new UserRegisteredException(LocalError.E003.getMessage());
                },
                () -> {
                    try {
                        response.setUser(userMapper.entityToDTO(userRepository.save(userMapper.requestToEntity(request))));
                    } catch (Exception e) {
                        throw new UserRegisteringException(LocalError.E004.getMessage());
                    }
                }
        );
        log.info("User saved");
        return response;
    }

    @Transactional
    public DeleteUserResponse deleteById(Long id) {
        log.info("Start deleting a user with ID: {}", id);
        userRepository.deleteById(id);
        return new DeleteUserResponse();
    }

    @Transactional
    public UpdateUserResponse update(Long id, UpdateUserRequest request) {
        log.info("Start updating an user");
        UpdateUserResponse response = new UpdateUserResponse();
        userRepository.findById(id).ifPresentOrElse(
                entity -> {
                    try {
                        userMapper.updateFromRequest(request, entity);
                        response.setUser(userMapper.entityToDTO(userRepository.save(entity)));
                    } catch (Exception e) {
                        throw new UserUpdatingException(LocalError.E005.getMessage());
                    }
                },
                () -> { throw new UserNotFoundException(LocalError.E002.getMessage()); }
        );
        log.info("User updated");
        return response;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }

}
