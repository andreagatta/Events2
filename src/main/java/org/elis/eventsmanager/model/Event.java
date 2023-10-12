package org.elis.eventsmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(nullable = false, updatable = false, name="creator_id")
    private User user;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Ticket> tickets = new ArrayList<>();

}
