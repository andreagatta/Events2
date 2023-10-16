package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateEventRequest {
    @NotBlank(message = "you have to fill the email field")
    @Email(message = "you have to insert a valid email")
    private String email;
    @NotBlank(message = "the password cannot be null or empty")
    private String password;
    @NotBlank(message = "title field can't be empty")
    private String title;
    @NotBlank(message = "DateTime field can't be empty")
    private LocalDateTime dateTime;
}
