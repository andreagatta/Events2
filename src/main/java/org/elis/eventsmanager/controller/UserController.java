package org.elis.eventsmanager.controller;

import jakarta.validation.Valid;
import org.elis.eventsmanager.dto.request.BlockUserRequest;
import org.elis.eventsmanager.dto.request.LoginRequest;
import org.elis.eventsmanager.dto.request.SignupRequest;
import org.elis.eventsmanager.dto.response.LoginResponse;
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

import static org.elis.eventsmanager.util.UtilPath.*;

@RestController
@RequestMapping("/user")
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
}
