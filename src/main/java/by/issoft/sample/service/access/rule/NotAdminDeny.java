package by.issoft.sample.service.access.rule;

import by.issoft.sample.domain.User;
import by.issoft.sample.domain.UserRole;

public class NotAdminDeny implements AccessDenyRule {
    @Override
    public boolean isDeny(User user) {
        return user.getUserRole() != UserRole.ADMIN;
    }
}
