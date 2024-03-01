package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    List<UserResponse> getAllUsersByUsername(String username);
}
