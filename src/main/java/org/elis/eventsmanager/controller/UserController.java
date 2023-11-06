package org.elis.eventsmanager.controller;

import jakarta.validation.Valid;
import org.elis.eventsmanager.dto.request.*;
import org.elis.eventsmanager.dto.response.LoginResponse;
import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.Role;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.security.TokenUtil;
import org.elis.eventsmanager.service.definition.UserService;
import org.elis.eventsmanager.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.elis.eventsmanager.util.UtilPath.*;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    TokenUtil tokenUtil;

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        User user = service.login(request);
        String token = tokenUtil.generateToken(user);
        LoginResponse lr = new LoginResponse();
        lr.setId(user.getId());
        lr.setEmail(user.getEmail());
        lr.setBlocked(user.isBlocked());
        lr.setRole(user.getRole().name());
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", token).body(lr);
    }

    @PostMapping(BLOCK_USER)
    public ResponseEntity<Void> blockUser(@Valid @RequestBody BlockUserRequest request, UsernamePasswordAuthenticationToken upat){
        upat.getPrincipal();
        service.blockUser(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(UNBLOCK_USER)
    public ResponseEntity<Void> unblockUser(@Valid @RequestBody UnblockUserRequest request, UsernamePasswordAuthenticationToken upat){
        upat.getPrincipal();
        service.unblockUser(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(SIGNUP)
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignupRequest request){
        service.signup(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/getRole")
    public ResponseEntity<Role> getRole(UsernamePasswordAuthenticationToken token){
        User user = (User) token.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(user.getRole());
    }

    @PostMapping("/all/user/joinEvent")
    public ResponseEntity<Void> joinEvent(@Valid @RequestBody JoinEventRequest request, UsernamePasswordAuthenticationToken token){
        User user = (User) token.getPrincipal();
        service.joinEvent(request, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/customer/getTickets")
    public ResponseEntity<List<Event>> getTickets(UsernamePasswordAuthenticationToken token){
        User user = (User) token.getPrincipal();
        List<Event> events = service.getTickets(user);
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

}
