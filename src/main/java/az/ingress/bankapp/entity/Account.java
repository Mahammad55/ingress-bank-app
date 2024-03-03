package az.ingress.bankapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "getAllAccounts",
        query = "select a from Account a join fetch a.user u join fetch a.cards c join fetch c.cardBenefits b"
)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String accountNumber;

    private double balance;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;
}