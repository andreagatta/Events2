package org.elis.eventsmanager.mapper;


import org.elis.eventsmanager.dto.response.EventDTO;
import org.elis.eventsmanager.model.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapper {
    public EventDTO toEventDTO (Event e) {
        EventDTO eDTO = new EventDTO();
        if(e==null)
            return eDTO;
        eDTO.setTitolo(e.getTitle());
        eDTO.setData_e_ora(e.getLocalDateTime().toString());
        return eDTO;
    }

    public Event toEvent (EventDTO eDTO){
        if (eDTO == null) return null;
        Event e = new Event();
        e.setTitle(eDTO.getTitolo());
        e.setLocalDateTime(LocalDateTime.parse(eDTO.getData_e_ora(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return e;
    }

    public List<EventDTO> toEventDTOList (List<Event> l){
        if(l==null) return new ArrayList<>();
        return l.stream().map(this::toEventDTO).toList();
    }
}
