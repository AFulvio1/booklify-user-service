package com.afulvio.booklify.userservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LocalError {

    E001("001", "it was not possible to recover the user"),
    E002("002", "it was not possible to recover the user for update"),
    E003("003", "Founded another user with this email"),
    E004("004", "Error saving user"),
    E005("005", "Error updating user");

    private final String code;
    private final String message;
}
