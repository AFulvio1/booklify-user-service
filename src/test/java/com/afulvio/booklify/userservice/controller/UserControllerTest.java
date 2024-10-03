package com.afulvio.booklify.userservice.controller;

import com.afulvio.booklify.userservice.BaseIntegrationTest;
import com.afulvio.booklify.userservice.dto.request.AuthRequest;
import com.afulvio.booklify.userservice.dto.request.SaveUserRequest;
import com.afulvio.booklify.userservice.dto.request.UpdateUserRequest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseIntegrationTest {

    private final String BASE_URL = "/api/users";

    @Test
    @Order(1)
    public void getByEmail_OK() throws Exception {
        this.mockMvc.perform(get(BASE_URL + "/get/{email}", "afulvio@booklify.org")
                        .characterEncoding("utf-8")
                        .contentType(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.firstname").value("Antonio"));
    }

    @Test
    @Order(2)
    public void getByEmail_KO() throws Exception {
        this.mockMvc.perform(get(BASE_URL + "/get/{email}", "ffulvio@booklify.org")
                        .characterEncoding("utf-8")
                        .contentType(MediaTypes.HAL_JSON)
                        .accept(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("it was not possible to recover the user"));
    }

    @Test
    @Order(3)
    public void save_OK() throws Exception {
        this.mockMvc.perform(post(BASE_URL + "/add")
                        .content(this.objectMapper.writeValueAsBytes(buildSaveUserRequest()))
                        .characterEncoding("utf-8")
                        .contentType(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.user.email").value("test@booklify.org"));
    }

    @Test
    @Order(4)
    public void save_KO() throws Exception {
        this.mockMvc.perform(post(BASE_URL + "/add")
                        .content(this.objectMapper.writeValueAsBytes(buildSaveUserRequest()))
                        .characterEncoding("utf-8")
                        .contentType(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Founded another user with this email"));
    }

    @Test
    @Order(5)
    public void update_OK() throws Exception {
        this.mockMvc.perform(put(BASE_URL + "/update/{id}", 2)
                        .content(this.objectMapper.writeValueAsBytes(buildUpdateUserRequest()))
                        .characterEncoding("utf-8")
                        .contentType(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.user.email").value("testrenamed@booklify.org"));
    }

    @Test
    @Order(6)
    public void update_KO() throws Exception {
        this.mockMvc.perform(put(BASE_URL + "/update/{id}", 3)
                        .content(this.objectMapper.writeValueAsBytes(buildUpdateUserRequest()))
                        .characterEncoding("utf-8")
                        .contentType(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("it was not possible to recover the user for update"));
    }

    @Test
    @Order(7)
    public void delete_OK() throws Exception {
        this.mockMvc.perform(delete(BASE_URL + "/delete/{id}", 2)
                        .content(this.objectMapper.writeValueAsBytes(buildUpdateUserRequest()))
                        .characterEncoding("utf-8")
                        .contentType(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(8)
    public void login_OK() throws Exception {
        this.mockMvc.perform(post(BASE_URL + "/login")
                        .content(this.objectMapper.writeValueAsBytes(builAuthRequest()))
                        .characterEncoding("utf-8")
                        .contentType(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(9)
    public void getAll_OK() throws Exception {
        this.mockMvc.perform(get(BASE_URL + "/get-all")
                        .characterEncoding("utf-8")
                        .contentType(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users.size()").value(1));
    }

    // BUILDERS --------------------------------------------------------------------------------------------------------

    private SaveUserRequest buildSaveUserRequest() {
        return SaveUserRequest.builder()
                .firstname("Test")
                .lastname("Test")
                .email("test@booklify.org")
                .password("TEST")
                .role("ADMIN")
                .build();
    }

    private UpdateUserRequest buildUpdateUserRequest() {
        return UpdateUserRequest.builder()
                .firstname("Test Renamed")
                .lastname("Test Renamed")
                .email("testrenamed@booklify.org")
                .password("TESTRENAMED")
                .role("USER")
                .build();
    }

    private AuthRequest builAuthRequest() {
        return AuthRequest.builder()
                .email("afulvio@booklify.org")
                .password("password123")
                .build();
    }

}
