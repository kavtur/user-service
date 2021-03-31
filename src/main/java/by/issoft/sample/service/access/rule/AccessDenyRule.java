package by.issoft.sample.service.access.rule;

import by.issoft.sample.domain.User;

public interface AccessDenyRule {

    boolean isDeny(User user);
}
