package org.elis.eventsmanager.service.implementation;

import jakarta.validation.Valid;
import org.elis.eventsmanager.dto.request.CreateEventRequest;
import org.elis.eventsmanager.dto.request.DeleteEventRequest;
import org.elis.eventsmanager.dto.request.EditEventRequest;
import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.repository.EventRepo;
import org.elis.eventsmanager.service.definition.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepo eventRepo;

    @Override
    public void createEvent(CreateEventRequest request, User user) {
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setLocalDateTime(LocalDateTime.parse(request.getDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) );
        event.setUser(user);

        try{
            eventRepo.save(event);
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "l'evento esiste già");
        }
    }

    @Override
    public void editEvent(EditEventRequest request) {
        Optional<Event> eventOptional = eventRepo.findByTitle(request.getTitleToEdit());

        if(eventOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event to edit not found");

        Event event = eventOptional.get();
        if(request.getTitle()!=null&&!request.getTitle().isBlank())
            event.setTitle(request.getTitle());
        if(request.getDateTime()!=null&&!request.getDateTime().isBlank())
        event.setLocalDateTime(LocalDateTime.parse(request.getDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        eventRepo.save(event);
    }

    @Override
    public void deleteEvent(@Valid @RequestBody DeleteEventRequest request) {
        Optional<Event> eventOptional = eventRepo.findByTitle(request.getTitle());

        if(eventOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event to delete not found");

        Event event = eventOptional.get();
        eventRepo.delete(event);
    }
}
