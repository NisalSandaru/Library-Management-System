package com.nisal.Library.Management.System.service;

import com.nisal.Library.Management.System.model.User;
import com.nisal.Library.Management.System.payload.dto.UserDTO;

import java.util.List;

public interface UserService {

    public User getCurrentUser() throws Exception;
    public List<UserDTO> getAllUsers();
}
