package az.ingress.bankapp.service.impl;


import az.ingress.bankapp.dto.request.UserRequest;
import az.ingress.bankapp.dto.response.UserResponse;
import az.ingress.bankapp.entity.User;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.UserMapper;
import az.ingress.bankapp.repository.UserRepository;
import az.ingress.bankapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> list = userRepository
                .findAll()
                .stream()
                .map(userMapper::entityToResponse)
                .toList();

        if (list.isEmpty()) {
            throw new NotFoundException(USER_NOT_FOUND.getMessage());
        }
        return list;
    }

    @Override
    public List<UserResponse> getAllUsersByUsername(String username) {
        List<UserResponse> userResponseList = userRepository
                .getAllUsersByUsername(username)
                .get()
                .stream()
                .map(userMapper::entityToResponse)
                .toList();

        if (userResponseList.isEmpty()) {
            throw new NotFoundException(USER_NOT_FOUND.getMessage().formatted(username));
        }

        return userResponseList;
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository
                .findUserById(id)
                .map(userMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage().formatted(id)));
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        userRepository.save(userMapper.requestToEntity(userRequest));
    }

    @Override
    public void updateStudent(Long id, UserRequest userRequest) {
        User user = userRepository
                .findUserById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage().formatted(id)));

        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository
                .findUserById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage().formatted(id)));
        userRepository.deleteById(id);
    }
}
