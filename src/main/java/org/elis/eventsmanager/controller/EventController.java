package org.elis.eventsmanager.controller;

import org.elis.eventsmanager.dto.response.EventDTO;
import org.elis.eventsmanager.mapper.EventMapper;
import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.service.definition.EventService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.elis.eventsmanager.util.UtilPath.*;

@RestController
@RequestMapping
public class EventController {

    @Autowired
    private EventMapper mapper;

    @Autowired
    private EventService service;
    @PostMapping(CREATE_EVENT)
    ResponseEntity<Void> aggiungiEvento(@RequestBody EventDTO eDTO, UsernamePasswordAuthenticationToken token){
        User u = (User) token.getPrincipal();
        eDTO.setCreator_id(u.getId());
        service.save(eDTO);
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    ResponseEntity<List<EventDTO>> findAll(){
        List<EventDTO> l = mapper.toEventDTOList(service.findAll());
        return ResponseEntity.status(200).body(l);
    }

    @GetMapping("/{id}")
    ResponseEntity<EventDTO> findById(@PathVariable long id){
        EventDTO e = mapper.toEventDTO(service.findById(id));
        return ResponseEntity.status(200).body(e);
    }
}
