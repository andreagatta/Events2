package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.Ticket;
import org.elis.eventsmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByEvent(Event event);
    List<Ticket> findAllByUser(User user);
}
