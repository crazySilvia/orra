package de.silvia.backend.security.repositories;

import de.silvia.backend.security.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends MongoRepository<User, String> {

    User findUserByUsername(String userName);
}