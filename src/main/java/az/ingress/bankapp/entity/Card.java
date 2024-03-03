package az.ingress.bankapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Account account;

    private String cardNumber;

    private String cardName;

    private String expirationDate;

    @OneToMany(mappedBy = "card")
    private Set<CardBenefit> cardBenefits;
}
