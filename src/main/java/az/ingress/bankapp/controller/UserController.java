package az.ingress.bankapp.controller;

import az.ingress.bankapp.dto.request.UserRequest;
import az.ingress.bankapp.dto.response.UserResponse;
import az.ingress.bankapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<UserResponse>> getAllUsersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getAllUsersByUsername(username));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable("id") Long userId,
                           @RequestBody UserRequest userRequest) {
        userService.updateStudent(userId, userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
    }
}
