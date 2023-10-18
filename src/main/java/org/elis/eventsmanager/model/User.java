package org.elis.eventsmanager.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Transactional
@Table(name="customUser")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private boolean isBlocked;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE})
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE})
    private List<Ticket> tickets = new ArrayList<>();

    //metodo che permette di automatizzare l'accesso dei vari ruoli su servizi scritti
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //il testo passato al costruttore del SGA vuole SEMPRE la scritta ROLE_*ruolo*
        SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_"+role);
        List<SimpleGrantedAuthority> list = List.of(sga);
        return list;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
