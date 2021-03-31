package by.issoft.sample.persistence;

import by.issoft.sample.domain.User;
import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUserStorage implements UserStorage {

    Map<String, User> users = new HashMap<>();

    @Override
    public Optional<User> findByUsername(String userName) {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getUserName().equals(userName)) {
                return Optional.of(entry.getValue());
            }
        }

        return Optional.empty();
    }

    @Override
    public String persist(User user) {
        final String id = UUID.randomUUID().toString();

        users.put(id, user);

        return user.getId();
    }

    @Override
    public User load(String id) {
        System.out.println("load from storage: " + this.getClass());

        final User user = users.get(id);
        Preconditions.checkNotNull(user);
        return user;
    }
}
