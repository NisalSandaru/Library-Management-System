package com.nisal.Library.Management.System.payload.dto;

import com.nisal.Library.Management.System.domain.AuthProvider;
import com.nisal.Library.Management.System.domain.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "fullName is required")
    private String fullName;

    @NotNull(message = "password is required")
    private String password;
    private UserRole role;
    private String phone;

    private String username;

    private LocalDateTime lastLogin;
}
