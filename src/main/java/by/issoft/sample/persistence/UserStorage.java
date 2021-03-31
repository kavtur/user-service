package by.issoft.sample.persistence;

import by.issoft.sample.domain.User;

import java.util.Optional;

public interface UserStorage {

    Optional<User> findByUsername(String userName);

    String persist(User user);

    User load(String id);
}
