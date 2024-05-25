package az.ingress.bankapp.controller;

import az.ingress.bankapp.dto.request.CardBenefitRequest;
import az.ingress.bankapp.dto.response.CardBenefitResponse;
import az.ingress.bankapp.service.CardBenefitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/card_benefits")
@RequiredArgsConstructor
public class CardBenefitController {
    private final CardBenefitService cardBenefitService;

    @GetMapping("/{id}")
    public ResponseEntity<CardBenefitResponse> getCardBenefitById(@PathVariable Long id) {
        return ResponseEntity.ok(cardBenefitService.getCardBenefitById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<CardBenefitResponse>> getAllCardBenefit() {
        return ResponseEntity.ok(cardBenefitService.getAllCardBenefit());
    }

    @PostMapping("/")
    public void saveCardBenefit(@Valid @RequestBody CardBenefitRequest cardBenefitRequest) {
        cardBenefitService.saveCardBenefit(cardBenefitRequest);
    }

    @PutMapping("/{id}")
    public void updateCardBenefit(@PathVariable Long id, @Valid @RequestBody CardBenefitRequest cardBenefitRequest) {
        cardBenefitService.updateCardBenefit(id, cardBenefitRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCardBenefit(@PathVariable Long id) {
        cardBenefitService.deleteCardBenefit(id);
    }
}
