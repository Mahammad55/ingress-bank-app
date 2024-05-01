package az.ingress.bankapp.service.impl;


import az.ingress.bankapp.dto.request.UserRequest;
import az.ingress.bankapp.dto.response.UserResponse;
import az.ingress.bankapp.entity.User;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.UserMapper;
import az.ingress.bankapp.repository.UserRepository;
import az.ingress.bankapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUser() {
        log.info("UserService.getAllUser.start");

        List<UserResponse> userResponseList = userRepository
                .findAll()
                .stream()
                .map(userMapper::entityToResponse)
                .toList();

        if (userResponseList.isEmpty()) {
            log.error("UserService.getAllUser.error -- user not found");
            throw new NotFoundException(USER_NOT_FOUND.getMessage());
        }
        return userResponseList;
    }

    @Override
    public List<UserResponse> getAllUserByUsername(String username) {
        log.info("UserService.getAllUserByUsername.start username:{}", username);

        List<UserResponse> userResponseList = userRepository
                .getAllUsersByUsername(username)
                .get()
                .stream()
                .map(userMapper::entityToResponse)
                .toList();

        if (userResponseList.isEmpty()) {
            log.error("UserService.getAllUserByUsername.error -- user not found username:{}", username);
            throw new NotFoundException(USER_NOT_FOUND.getMessage().formatted(username));
        }

        return userResponseList;
    }

    @Override
    public UserResponse getUserById(Long id) {
        log.info("UserService.getUserById.start id:{}", id);

        return userRepository
                .findUserById(id)
                .map(userMapper::entityToResponse)
                .orElseThrow(() -> {
                            log.error("UserService.getUserById.error -- user not found id:{}", id);
                            return new NotFoundException(USER_NOT_FOUND.getMessage().formatted(id));
                        }
                );
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        log.info("UserService.saveUser.start username:{}", userRequest.getUsername());

        userRepository.save(userMapper.requestToEntity(userRequest));

        log.info("UserService.saveUser.success username:{}", userRequest.getUsername());
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        log.info("UserService.updateUser.start id:{} and username:{}", id, userRequest.getUsername());

        userRepository
                .findUserById(id)
                .orElseThrow(() -> {
                    log.error("UserService.updateUser.error -- user not found id:{}", id);
                    return new NotFoundException(USER_NOT_FOUND.getMessage().formatted(id));
                });

        User user = userMapper.requestToEntity(userRequest);
        user.setId(id);
        userRepository.save(user);

        log.info("UserService.updateUser.success id:{} and username:{}", id, userRequest.getUsername());
    }

    @Override
    public void deleteUser(Long id) {
        log.info("UserService.deleteUser.start id:{}", id);

        userRepository
                .findUserById(id)
                .orElseThrow(() -> {
                    log.error("UserService.deleteUser.error -- user not found id:{}", id);
                    return new NotFoundException(USER_NOT_FOUND.getMessage().formatted(id));
                });

        userRepository.deleteById(id);

        log.info("UserService.deleteUser.success id:{}", id);
    }
}
