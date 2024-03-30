package com.mehdi.jpauserroles.service;

import com.mehdi.jpauserroles.entities.Role;
import com.mehdi.jpauserroles.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username , String roleName);
    User authenticate(String userName , String password);
}
