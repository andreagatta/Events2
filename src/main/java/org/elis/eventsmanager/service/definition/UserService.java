package org.elis.eventsmanager.service.definition;

import org.elis.eventsmanager.dto.request.*;
import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.Ticket;
import org.elis.eventsmanager.model.User;
import org.hibernate.mapping.Join;

import java.util.List;

public interface UserService {
    //login
    public User login(LoginRequest request);
    //signup
    public void signup(SignupRequest request);
    //block
    public void blockUser(BlockUserRequest request);
    //unblock
    public void unblockUser(UnblockUserRequest request);
    //changeRole
    public void changeRole(ChangeRoleRequest request);
    //partecipa
    public void joinEvent(JoinEventRequest request, User user);
    //cancella prenotazione
    public void cancelTicket(CancelTicketRequest request);
    //trova biglietti attivi
    public List<Event> getTickets(User user);
    //trova utente da email
    public User getUserByEmail(String email);
}
