package org.elis.eventsmanager.controller;

import jakarta.validation.Valid;
import org.elis.eventsmanager.dto.request.CreateEventRequest;
import org.elis.eventsmanager.dto.request.DeleteEventRequest;
import org.elis.eventsmanager.dto.request.EditEventRequest;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.service.definition.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService service;

    @PostMapping("/admin/createEvent")
    public ResponseEntity<Void> createEvent(@Valid @RequestBody CreateEventRequest request, UsernamePasswordAuthenticationToken token){
        User user = (User) token.getPrincipal();
        service.createEvent(request, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/admin/editEvent")
    public ResponseEntity<Void> editEvent(@Valid @RequestBody EditEventRequest request, UsernamePasswordAuthenticationToken token){
        service.editEvent(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/admin/deleteEvent")
    public ResponseEntity<Void> deleteEvent(@Valid @RequestBody DeleteEventRequest request, UsernamePasswordAuthenticationToken token){
        service.deleteEvent(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
