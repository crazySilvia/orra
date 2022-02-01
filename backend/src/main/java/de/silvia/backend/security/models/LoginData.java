package de.silvia.backend.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginData {
    String name;
    String password;

    public <E> LoginData(String name, String password, List<E> api_rraedwrite) {
    }

    public String getName() {
        return name;
    }
}