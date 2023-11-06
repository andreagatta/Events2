package org.elis.eventsmanager.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDateTime localDateTime;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(nullable = false, updatable = false, name="creator_id")
    private User user;

    @ManyToMany(mappedBy = "tickets")
    private List<User> participants = new ArrayList<>();
}
