package org.elis.eventsmanager.dto.response;

import lombok.Data;

@Data
public class EventDTO {
    private String titolo;
    private String data_e_ora;
    private long creator_id;
}

