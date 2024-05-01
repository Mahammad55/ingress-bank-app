package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.request.UserRequest;
import az.ingress.bankapp.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserById(Long id);

    List<UserResponse> getAllUser();

    List<UserResponse> getAllUserByUsername(String username);

    void saveUser(UserRequest userRequest);

    void updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}
