package org.elis.eventsmanager.service.implementation;

import jakarta.validation.constraints.Email;
import org.elis.eventsmanager.dto.request.*;
import org.elis.eventsmanager.exception.UserNotFoundException;
import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.Role;
import org.elis.eventsmanager.model.Ticket;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.repository.EventRepo;
import org.elis.eventsmanager.repository.TicketRepo;
import org.elis.eventsmanager.repository.UserRepo;
import org.elis.eventsmanager.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private TicketRepo ticketRepo;

    @Override
    public User login(LoginRequest request) {
        Optional<User> user = userRepo.findByEmailAndPassword(request.getEmail(), request.getPassword());
        return user.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void signup(SignupRequest request) {

        String password = request.getPassword();
        String password2 = request.getPassword2();

        if(!password.equals(password2)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "passwords must correspond");
        }

        User user = new User();
        user.setNome(request.getNome());
        user.setCognome(request.getCognome());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.CUSTOMER);
        user.setBlocked(false);

        try{
            userRepo.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "the user already exist");
        }
    }

    @Override
    public void blockUser(BlockUserRequest request) {
        Optional<User> optionalUser = userRepo.findByEmail(request.getEmailToBlock());
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();

        user.setBlocked(true);

        try{
            userRepo.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException((HttpStatus.INTERNAL_SERVER_ERROR), "SQL EXCEPTION");
        }

    }

    @Override
    public void unblockUser(UnblockUserRequest request) {
        Optional<User> optionalUser = userRepo.findByEmail(request.getEmailToUnblock());
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();

        user.setBlocked(false);

        try{
            userRepo.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException((HttpStatus.INTERNAL_SERVER_ERROR), "SQL EXCEPTION");
        }
    }

    @Override
    public void changeRole(ChangeRoleRequest request) {
        Optional<User> optionalUser = userRepo.findByEmail(request.getEmailToChangeRole());
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();

        user.setRole(Role.valueOf(request.getRole()));

        try{
            userRepo.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException((HttpStatus.INTERNAL_SERVER_ERROR), "SQL EXCEPTION");
        }
    }

    @Override
    public void joinEvent(JoinEventRequest request, User user) {
        Optional<Event> eventOptional = eventRepo.findByTitle(request.getTitle());

        if(eventOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event not found");
        }

        Event event = eventOptional.get();
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setUser(user);

        try{
            ticketRepo.save(ticket);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "SQL Exception");
        }

    }

    @Override
    public void cancelTicket(CancelTicketRequest request) {

    }

    @Override
    public List<Event> getTickets(User user) {
        List<Ticket> tickets = ticketRepo.findAllByUser(user);
        List<Event> events = new ArrayList<>();
        for(Ticket t : tickets){
            events.add(t.getEvent());
        }
        return events;
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> u = userRepo.findByEmail(email);
        return u.orElseThrow(UserNotFoundException::new);
    }
}
