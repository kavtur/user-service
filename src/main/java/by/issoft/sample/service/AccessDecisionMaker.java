package by.issoft.sample.service;

import by.issoft.sample.domain.User;
import by.issoft.sample.domain.UserRole;
import by.issoft.sample.domain.UserStatus;
import by.issoft.sample.service.access.rule.AccessDenyRule;

import java.util.List;

public class AccessDecisionMaker {

    private final List<AccessDenyRule> denyRules;

    public AccessDecisionMaker(List<AccessDenyRule> denyRules) {
        this.denyRules = denyRules;
    }

    public boolean hasAccess(User user) {
       for(AccessDenyRule accessDenyRule: denyRules) {
           if (accessDenyRule.isDeny(user)) {
               return false;
           }
       }

        return true;
    }

}
