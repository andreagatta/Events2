package org.elis.eventsmanager.mapper;

import org.elis.eventsmanager.dto.request.SignupRequest;
import org.elis.eventsmanager.dto.response.ClienteDTO;
import org.elis.eventsmanager.dto.response.LoginResponse;
import org.elis.eventsmanager.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public ClienteDTO toClienteDTO(User u){
        ClienteDTO cDto = new ClienteDTO();
        //converti
        return cDto;
    }

    public List<ClienteDTO> toClienteDTOList(List<User> l){
       return l.stream().map(this::toClienteDTO).toList();
    }

    public User fromSignupRequest (SignupRequest request){
        User u = new User();
        u.setNome(request.getNome());
        return u;
    }

    public LoginResponse toLoginResponse (User u){
        LoginResponse lr = new LoginResponse();
        lr.setEmail(u.getEmail());
        lr.setRole(u.getRole().toString());
        lr.setId(u.getId());
        lr.setBlocked(u.isBlocked());
        return lr;
    }
}
