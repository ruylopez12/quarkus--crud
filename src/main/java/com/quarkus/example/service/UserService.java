package com.quarkus.example.service;

import com.quarkus.example.dto.FilterRequestDTO;
import com.quarkus.example.dto.UserDTO;
import com.quarkus.example.entity.User;
import com.quarkus.example.exception.UserNotFoundException;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User getUserById(long id) throws UserNotFoundException;

    User saveUser(UserDTO userDTO);

    List<UserDTO> filter(FilterRequestDTO filterRequestDTO);

    String loginUser(String username,String password) throws IOException;
}
