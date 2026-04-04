package com.nisal.Library.Management.System.service;

import com.nisal.Library.Management.System.exception.UserException;
import com.nisal.Library.Management.System.payload.dto.UserDTO;
import com.nisal.Library.Management.System.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String password) throws UserException;
    AuthResponse signup(UserDTO req) throws UserException;

    void createPasswordResetToken(String email) throws UserException;
    void resetPassword(String token, String newPassword) throws Exception;
}
