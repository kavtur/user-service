package by.issoft.sample.persistence;

import by.issoft.sample.domain.User;

import java.io.FileNotFoundException;
import java.util.Optional;

public class InFileUserStorage implements UserStorage {
    @Override
    public Optional<User> findByUsername(String userName)  {
        return Optional.empty();
    }

    @Override
    public String persist(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User load(String id) {
        throw new UnsupportedOperationException();
    }
}
