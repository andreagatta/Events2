package org.elis.eventsmanager.service.definition;

import org.elis.eventsmanager.dto.request.CreateEventRequest;
import org.elis.eventsmanager.dto.request.DeleteEventRequest;
import org.elis.eventsmanager.dto.request.EditEventRequest;

public interface EventService {
    //crea
    public void createEvent(CreateEventRequest request);
    //modifica
    public void editEvent(EditEventRequest request);
    //elimina
    public void deleteEvent(DeleteEventRequest request);
}
