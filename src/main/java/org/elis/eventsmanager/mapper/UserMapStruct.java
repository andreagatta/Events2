package org.elis.eventsmanager.mapper;

import org.elis.eventsmanager.dto.request.SignupRequest;
import org.elis.eventsmanager.dto.response.ClienteDTO;
import org.elis.eventsmanager.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapStruct {
    public User fromSignupRequest (SignupRequest request);
    public List<ClienteDTO> toClienteDTOList(List<User> l);

}
