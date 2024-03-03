package az.ingress.bankapp.repository;

import az.ingress.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(name = "getAllAccounts")
    Optional<List<Account>> findAllByNamedQuery();
}
