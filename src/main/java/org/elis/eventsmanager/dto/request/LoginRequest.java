package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "you have to fill the email field")
    @Email(message = "you have to insert a valid email")
    private String email;
    @NotBlank(message = "the password cannot be null or empty")
    private String password;
}
