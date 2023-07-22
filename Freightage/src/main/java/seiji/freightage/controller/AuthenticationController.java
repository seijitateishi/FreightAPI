package seiji.freightage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import seiji.freightage.configuration.security.AuthenticationRequest;
import seiji.freightage.configuration.security.AuthenticationResponse;
import seiji.freightage.configuration.security.RegisterRequest;
import seiji.freightage.service.AuthenticationService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a user",
            tags = {"Authentication"},
            description = "Create a new user using a 'name', a 'email' and a 'password' as JSON",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New user created")
            })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Operation(summary = "Authenticate a user",
            tags = {"Authentication"},
            description = "Authenticate a user using a 'email' and a 'password' as JSON",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User authenticated")
            })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
