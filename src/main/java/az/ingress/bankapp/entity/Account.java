package az.ingress.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name = "account-user-cards",
        attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value = "cards", subgraph = "cardBenefitsSubGraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "cardBenefitsSubGraph",
                        attributeNodes = @NamedAttributeNode("cardBenefits")
                )
        }
)
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