package de.silvia.backend.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    @NonNull
    private String firstName;

    @NotNull
    @NonNull
    private String lastName;

    @NotNull
    @NonNull
    private String userName;

    @NotNull
    @NonNull
    private String password;
    private String matchingPassword;

    @NotNull
    @NonNull
    private String email;

}
