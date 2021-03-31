package by.issoft.sample.persistence;

import by.issoft.sample.domain.User;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserTimeLimittedCache implements UserStorage {

    private final long ttl;
    private final UserStorage userStorage;

    private Map<String, CacheEntry> cache = new HashMap<>();

    public UserTimeLimittedCache(long ttl, UserStorage userStorage) {
        this.ttl = ttl;
        this.userStorage = userStorage;
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        return userStorage.findByUsername(userName);
    }

    @Override
    public String persist(User user) {
        return userStorage.persist(user);
    }

    @Override
    public User load(String id) {
        final CacheEntry cacheEntry = cache.get(id);
        if (cacheEntry != null && !cacheEntry.isExpired(ttl)) {
            return cacheEntry.getUser();
        }

        final User loaded = userStorage.load(id);
        cache.put(id, new CacheEntry(loaded));

        return loaded;
    }
}

class CacheEntry {

    private final Instant time;
    private final User user;

    public CacheEntry(User user) {
        this.time = Instant.now();
        this.user = user;
    }

    public Instant getTime() {
        return time;
    }

    public User getUser() {
        return user;
    }

    boolean isExpired(long ttl) {
        return time.plusMillis(ttl).isBefore(Instant.now());
    }
}
