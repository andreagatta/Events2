package org.elis.eventsmanager.service.implementation;

import jakarta.validation.Valid;
import org.elis.eventsmanager.dto.request.CreateEventRequest;
import org.elis.eventsmanager.dto.request.DeleteEventRequest;
import org.elis.eventsmanager.dto.request.EditEventRequest;
import org.elis.eventsmanager.dto.response.EventDTO;
import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.repository.EventRepo;
import org.elis.eventsmanager.service.definition.EventService;
import org.elis.eventsmanager.service.definition.GenericCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService, GenericCall {

    @Value("${service.url.event}")
    private String pathGenerico;
    @Override
    public Event findById(long id) {
        String path=pathGenerico+"/"+id;
        Event e = callGet(path, null, Event.class);
        return e;
    }

    @Override
    public List<Event> findAll() {
        Event[] events = callGet(pathGenerico, null, Event[].class);
        return List.of(events);
    }

    @Override
    public void save(EventDTO e) {
        callPost(pathGenerico,e);
    }

    @Override
    public void update(Event e) {
        callPut(pathGenerico,e);
    }
}
