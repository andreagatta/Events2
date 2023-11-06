package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {
    Optional<Event> findById(long id);
    Optional<Event> findByTitle(String title);
    List<Event> findAllByUser(User user);
}
