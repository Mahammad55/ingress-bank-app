package az.ingress.bankapp.service.impl;


import az.ingress.bankapp.dto.response.UserResponse;
import az.ingress.bankapp.mapper.UserMapper;
import az.ingress.bankapp.repository.UserRepository;
import az.ingress.bankapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::entityToResponse)
                .toList();
    }
}
