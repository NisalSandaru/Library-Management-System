package com.nisal.Library.Management.System.controller;

import com.nisal.Library.Management.System.exception.UserException;
import com.nisal.Library.Management.System.payload.dto.UserDTO;
import com.nisal.Library.Management.System.payload.request.ForgotPasswordRequest;
import com.nisal.Library.Management.System.payload.request.LoginRequest;
import com.nisal.Library.Management.System.payload.request.ResetPasswordRequest;
import com.nisal.Library.Management.System.payload.response.ApiResponse;
import com.nisal.Library.Management.System.payload.response.AuthResponse;
import com.nisal.Library.Management.System.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
            @RequestBody @Valid UserDTO req
            ) throws UserException {
        AuthResponse res = authService.signup(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody @Valid LoginRequest req
    ) throws UserException {
        AuthResponse res = authService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @RequestBody @Valid ForgotPasswordRequest req
    ) throws UserException {
        authService.createPasswordResetToken(req.getEmail());
        ApiResponse res = new ApiResponse(
                "A Rest link was sent to your email.", true
        );
        return ResponseEntity.ok(res);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestBody @Valid ResetPasswordRequest req
    ) throws UserException, Exception {
        authService.resetPassword(req.getToken(), req.getPassword());
        ApiResponse res = new ApiResponse(
                "Password reset successful.", true
        );
        return ResponseEntity.ok(res);
    }

}
