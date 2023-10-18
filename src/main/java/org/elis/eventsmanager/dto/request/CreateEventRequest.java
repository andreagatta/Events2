package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateEventRequest {
    @NotBlank(message = "title field can't be empty")
    private String title;
    @NotBlank(message = "DateTime field can't be empty")
    private String dateTime;
}
