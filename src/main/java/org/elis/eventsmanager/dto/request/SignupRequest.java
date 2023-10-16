package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "name field can't be empty")
    private String nome;
    @NotBlank(message = "surname field can't be empty")
    private String cognome;
    @NotBlank(message = "email field can't be empty")
    @Email(message = "insert a valid email")
    private String email;
    @NotBlank(message = "password field can't be empty")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,15})", message = "not valid password")
    private String password;
    @NotBlank(message = "repeated password field can't be empty")
    private String password2;

}
