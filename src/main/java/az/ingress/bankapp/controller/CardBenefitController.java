package az.ingress.bankapp.controller;

import az.ingress.bankapp.service.CardBenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/card_benefits")
@RequiredArgsConstructor
public class CardBenefitController {
    private final CardBenefitService cardBenefitService;
}
