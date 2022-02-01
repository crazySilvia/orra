package de.silvia.backend.security.repositories;

import de.silvia.backend.api.UserDto;
import de.silvia.backend.security.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<UserDto> insert(UserDto userDto);
}