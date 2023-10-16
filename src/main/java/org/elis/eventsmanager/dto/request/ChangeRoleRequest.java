package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.elis.eventsmanager.model.Role;

@Data
public class ChangeRoleRequest {
    @NotBlank(message = "user email can't be empty")
    private String emailToChangeRole;
    @NotBlank(message = "role field can't be empty")
    private String role;
}
