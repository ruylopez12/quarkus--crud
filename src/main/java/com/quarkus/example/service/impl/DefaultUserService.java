package com.quarkus.example.service.impl;

import com.quarkus.example.dto.FilterRequestDTO;
import com.quarkus.example.dto.UserDTO;
import com.quarkus.example.entity.User;
import com.quarkus.example.exception.UserNotFoundException;
import com.quarkus.example.repository.UserRepository;
import com.quarkus.example.service.UserService;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.jwt.Claims;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Inject
    EntityManager entityManager;

    @Inject
    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long id) throws UserNotFoundException {
        return userRepository.findByIdOptional(id).orElseThrow(() -> new UserNotFoundException("There user doesn't exist"));
    }

    @Transactional
    @Override
    public User saveUser(UserDTO userDTO) {
        User user=new User();
        user.setFirstName(userDTO.getFirstName());
        user.setAge(userDTO.getAge());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        userRepository.persistAndFlush(user);
        return user;
    }

    @Override
    public List<UserDTO> filter(FilterRequestDTO filterRequestDTO) {
        return userRepository.filter(filterRequestDTO);
    }

    @Override
    public String loginUser(String username,String password) throws IOException {
        if(username!=null && password!=null){
            Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :search");
            q.setParameter("search", username);
            List user=q.getResultList();
            List<User> userList = new ArrayList<>();
            for (Object obj : user) {
                if (obj instanceof User) {
                    userList.add((User) obj);
                }
            }
            String name=userList.get(0).getUsername();
            String role=userList.get(0).getRole();
            String pass=userList.get(0).getPassword();
            if(BcryptUtil.matches(password,pass)){
                String token = Jwt.issuer("user")
                        .groups(Set.of(role))
                        .claim(Claims.email,name)
                        .sign();
                return token;
            }
            else {
                throw new SecurityException("password wrong");
            }
        }
        else {
            throw new IOException("input not valid");
        }
    }

}
