package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {
    Optional<Event> findById(long id);
    Optional<Event> findByTitle(String title);
}
