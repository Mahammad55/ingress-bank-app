package az.ingress.bankapp.repository;

import az.ingress.bankapp.entity.Card;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @EntityGraph(attributePaths = {"cardBenefits"})
    @Query("select c from Card c")
    Optional<List<Card>> findAllCards();
}
