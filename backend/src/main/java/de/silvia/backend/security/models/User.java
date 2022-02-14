package de.silvia.backend.security.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("User")
public class User implements UserDetails {

    @Id
    String username;
    String firstname;
    String lastname;
    String email;
    String password;
    @Transient
    List<GrantedAuthority> authorities;
    boolean enabled;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;

    public static User newUser(String firstname, String email, String lastname, String username, String password,
                               List<GrantedAuthority> authorities) {
        return User.builder()
                .username(username).password(password).firstname(firstname).lastname(lastname).email(email)
                .authorities(authorities)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }

}