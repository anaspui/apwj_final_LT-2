package dev.controller;

import dev.domain.User;
import dev.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void intiBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> addUser(@Valid @RequestBody User newUser, BindingResult bindingResult) {
        // System.out.println(newUser);

        if (bindingResult.hasErrors()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid Request");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        userService.create(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message", "User created successfully"));
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, List<?>>> getAllUsers() {
        List<User> users = userService.getAll();

        Map<String, List<?>> response = new HashMap<>();
        response.put("users", users);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getOne(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/count")
    public ResponseEntity<Map<String, Long>> getUserCount() {
        long userCount = userService.countUsers();

        Map<String, Long> response = Collections.singletonMap("User Count", userCount);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@RequestBody User updatedUser) {
        boolean updated = userService.updateUser(updatedUser);

        if (updated) {
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        boolean deleted = userService.delete(id);

        if (deleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
