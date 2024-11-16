package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmentor.spring.boot_security.demo.Service.UserService;
import ru.itmentor.spring.boot_security.demo.exception.UserIncorrectData;
import ru.itmentor.spring.boot_security.demo.exception.UserNotFoundException;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        List<User> allUsers = userService.findAll();
        return allUsers;
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id){
        User user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        return user;
    }
    @PostMapping("/users")
    @ResponseBody
    public User addNewUser (@RequestBody User user){
        return userService.save(user);
    }
    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
       return userService.update(user);
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user=userService.delete(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        return "Users with ID=" + id + " was deleted";
    }

}
