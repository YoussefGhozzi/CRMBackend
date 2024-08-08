package project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import project.spring.entités.User;

import project.spring.service.UserService;


@Controller

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/GetUsers")
    @ResponseBody
    public List<User> getAllUsers(@RequestParam String role) {
        return userService.getallUser(role);
    }

 
    @PostMapping("/AjoutUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.AjouteUser(user );
        return ResponseEntity.ok("User added successfully");
    }
    @DeleteMapping("/DeleteUser/{id}")
    @ResponseBody
    public void deleteUsers(@PathVariable("id") long idUser) {
        userService.DeleteUserById(idUser);
    }
    
  
}
       

        

