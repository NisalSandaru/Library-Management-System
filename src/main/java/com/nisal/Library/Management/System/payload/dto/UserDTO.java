package com.nisal.Library.Management.System.payload.dto;

import com.nisal.Library.Management.System.domain.AuthProvider;
import com.nisal.Library.Management.System.domain.UserRole;
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
    private String email;
    private String fullName;
    private String password;
    private UserRole role;
    private String phone;

    private String username;

    private LocalDateTime lastLogin;
}
