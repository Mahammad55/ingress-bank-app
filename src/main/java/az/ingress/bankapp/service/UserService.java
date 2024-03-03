package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.request.UserRequest;
import az.ingress.bankapp.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    List<UserResponse> getAllUsersByUsername(String username);

    UserResponse getUserById(Long id);

    void saveUser(UserRequest userRequest);

    void updateStudent(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}
