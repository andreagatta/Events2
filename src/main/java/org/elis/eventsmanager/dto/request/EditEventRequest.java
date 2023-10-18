package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditEventRequest {
    @NotBlank(message = "title of event to edit can't be blank")
    private String titleToEdit;
    private String title;
    private String dateTime;
}
