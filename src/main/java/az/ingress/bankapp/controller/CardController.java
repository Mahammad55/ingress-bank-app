package az.ingress.bankapp.controller;

import az.ingress.bankapp.dto.request.CardRequest;
import az.ingress.bankapp.dto.response.CardResponse;
import az.ingress.bankapp.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<CardResponse>> getAllCard() {
        return ResponseEntity.ok(cardService.getAllCard());
    }

    @PostMapping("/")
    @ResponseStatus(CREATED)
    public void saveCard(@Valid @RequestBody CardRequest cardRequest) {
        cardService.saveCard(cardRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateCard(@PathVariable Long id, @Valid @RequestBody CardRequest cardRequest) {
        cardService.updateCard(id, cardRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
