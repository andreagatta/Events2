package org.elis.eventsmanager.service.definition;

import org.elis.eventsmanager.dto.request.CreateEventRequest;
import org.elis.eventsmanager.dto.request.DeleteEventRequest;
import org.elis.eventsmanager.dto.request.EditEventRequest;
import org.elis.eventsmanager.dto.response.EventDTO;
import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.User;

import java.util.List;

public interface EventService {
    public Event findById(long id);
    public List<Event> findAll();
    public void save(EventDTO e);
    public void update(Event e);
}
