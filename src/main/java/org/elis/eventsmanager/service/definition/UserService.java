package org.elis.eventsmanager.service.definition;

import org.elis.eventsmanager.dto.request.*;
import org.elis.eventsmanager.model.User;
import org.hibernate.mapping.Join;

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
    public void joinEvent(JoinEventRequest request);
    //cancella prenotazione
    public void cancelTicket(CancelTicketRequest request);
    //trova utente da email
    public User getUserByEmail(String email);
}
