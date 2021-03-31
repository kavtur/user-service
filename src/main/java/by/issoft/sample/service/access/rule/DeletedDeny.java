package by.issoft.sample.service.access.rule;

import by.issoft.sample.domain.User;
import by.issoft.sample.domain.UserStatus;

public class DeletedDeny implements AccessDenyRule {

    @Override
    public boolean isDeny(User user) {
        return user.getStatus() == UserStatus.DELETED;
    }
}
