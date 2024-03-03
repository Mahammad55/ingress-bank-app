package az.ingress.bankapp.repository;

import az.ingress.bankapp.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(name = "getAllAccounts")
    Optional<List<Account>> findAllByNamedQuery();

    @EntityGraph(value = "account-user-cards", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select a from Account a")
    Optional<List<Account>> findAllAccountsByCustomGraph();
}
