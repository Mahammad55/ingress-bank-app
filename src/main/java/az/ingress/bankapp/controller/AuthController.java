package az.ingress.bankapp.controller;

import az.ingress.bankapp.config.JwtService;
import az.ingress.bankapp.dto.request.AuthRequest;
import az.ingress.bankapp.dto.response.AuthResponse;
import az.ingress.bankapp.service.impl.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        MyUserDetails user = (MyUserDetails) authenticate.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        return new AuthResponse(user.getUsername(), accessToken);
    }
}
