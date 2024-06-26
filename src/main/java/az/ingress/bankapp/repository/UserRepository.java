package az.ingress.bankapp.repository;

import az.ingress.bankapp.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(name = "getUserByUsername")
    Optional<List<User>> getAllUsersByUsername(@Param("username") String username);

    @Query(value = "select u from User u where u.id=:id")
    Optional<User> findUserById(Long id);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findUserByUsername(String username);
}
