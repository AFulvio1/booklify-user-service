package com.afulvio.booklify.userservice.configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BooklifyPasswordEncoder extends BCryptPasswordEncoder {
}
