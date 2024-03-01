package az.ingress.bankapp.mapper;

import az.ingress.bankapp.dto.response.UserResponse;
import az.ingress.bankapp.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse entityToResponse(User user);
}
