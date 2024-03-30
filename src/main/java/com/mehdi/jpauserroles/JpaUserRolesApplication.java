package com.mehdi.jpauserroles;

import com.mehdi.jpauserroles.entities.Role;
import com.mehdi.jpauserroles.entities.User;
import com.mehdi.jpauserroles.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaUserRolesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaUserRolesApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            User u = new User();
            u.setUsername("user1");
            u.setPassword("123456");
            userService.addNewUser(u);

            User u2 = new User();
            u2.setUsername("admin");
            u2.setPassword("123456");
            userService.addNewUser(u2);

            Stream.of("student","user","admin").forEach(r->{
                Role role = new Role();
                role.setRoleName(r);
                userService.addNewRole(role);
            });
            userService.addRoleToUser("user1","student");
            userService.addRoleToUser("user1","user");
            userService.addRoleToUser("admin","admin");
            userService.addRoleToUser("admin","user");


            try{
                User user = userService.authenticate("user1","123456");
                System.out.println("user authentifié");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println("roles =>");
                user.getRoles().forEach(r->{
                    System.out.println("role=>"+r.toString());
                });
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        };
    }

}
