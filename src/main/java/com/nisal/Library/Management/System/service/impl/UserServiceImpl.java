package com.nisal.Library.Management.System.service.impl;

import com.nisal.Library.Management.System.mapper.UserMapper;
import com.nisal.Library.Management.System.model.User;
import com.nisal.Library.Management.System.payload.dto.UserDTO;
import com.nisal.Library.Management.System.repository.UserRepository;
import com.nisal.Library.Management.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user==null){
            throw new Exception("user not found!");
        }
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
