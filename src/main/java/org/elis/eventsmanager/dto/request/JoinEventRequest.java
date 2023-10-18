package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JoinEventRequest {
    @NotBlank(message = "event title can't be empty")
    private String title;
}
