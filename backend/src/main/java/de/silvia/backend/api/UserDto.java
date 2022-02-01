package de.silvia.backend.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Id
    @NotNull
    private String userName;

    @NotNull
    private String password;


    @NotNull
    private String email;

}
