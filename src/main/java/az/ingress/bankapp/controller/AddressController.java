package az.ingress.bankapp.controller;

import az.ingress.bankapp.dto.request.AddressRequest;
import az.ingress.bankapp.dto.response.AddressResponse;
import az.ingress.bankapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressResponse>> getAllAddress() {
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAddress(@RequestBody AddressRequest addressRequest) {
        addressService.saveAddress(addressRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateAddress(@PathVariable Long id, @RequestBody AddressRequest addressRequest) {
        addressService.updateAddress(id, addressRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
