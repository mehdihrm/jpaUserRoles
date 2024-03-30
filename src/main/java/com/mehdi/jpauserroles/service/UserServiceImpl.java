package com.mehdi.jpauserroles.service;

import com.mehdi.jpauserroles.entities.Role;
import com.mehdi.jpauserroles.entities.User;
import com.mehdi.jpauserroles.repositories.RoleRepository;
import com.mehdi.jpauserroles.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user =  this.findUserByUsername(username);
        Role role = findRoleByRoleName(roleName);
        if(user.getRoles()!=null)
        {
            user.getRoles().add(role);
            role.getUsers().add(user); // boucle bidirectionnel

        }
        // userRepository.save(user); == code parfait mais ici ça se fait automatiquement
    }

    @Override
    public User authenticate(String userName, String password) {
        User user = userRepository.findByUsername(userName);
        if (user==null) throw new RuntimeException("failed");
        if(user.getPassword().equals(password)){
            return  user ;
        }
        else {
            throw  new RuntimeException("failed");
        }
    }
}
