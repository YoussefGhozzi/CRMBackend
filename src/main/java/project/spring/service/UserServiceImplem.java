package project.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import project.spring.entités.User;
import project.spring.repository.UserRepository;

@Service
public class UserServiceImplem implements UserService {

    @Autowired
    UserRepository RepUser;

   
    
    
    @Override
    public User AjouteUser(User u) {
        return RepUser.save(u);
    }

    @Override
    public void DeleteUser(User u) {
        RepUser.delete(u);
    }

    @Override
    public void DeleteUserById(long id) {
        RepUser.deleteById(id);
    }

    @Override
    public List<User> getallUser(String role) {
        System.out.println("Service layer - role: " + role);
        List<User> users = RepUser.findByRole(role);
        System.out.println("Users found: " + users);
        return users;
    }
    public Optional<User> findByEmail(String email) {
        return RepUser.findByEmail(email);
    }
    
    public String getUserRoleByEmail(String email) {
       Optional <User >user = RepUser.findByEmail(email);
        
        
        return null; // Ou une valeur par défaut comme "ROLE_USER"

  
    }
    
}
