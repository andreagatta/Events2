package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BlockUserRequest {
    @NotBlank(message = "user email can't be empty")
    @Email(message = "insert a valid email")
    private String emailToBlock;
}
