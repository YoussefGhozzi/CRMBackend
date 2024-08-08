package project.spring.service;

import java.util.List;



import project.spring.entités.User;

public interface UserService {
    User AjouteUser(User u);
    void DeleteUser(User u);
    void DeleteUserById(long id);
    List<User> getallUser(String role);
    String  getUserRoleByEmail(String email);
	

}
