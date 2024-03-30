package com.quarkus.example.controller;

import com.quarkus.example.dto.FilterRequestDTO;
import com.quarkus.example.dto.UserDTO;
import com.quarkus.example.entity.User;
import com.quarkus.example.exception.UserNotFoundException;
import com.quarkus.example.service.UserService;
import jakarta.inject.Inject;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;

import java.io.IOException;
import java.util.List;

@Path("/v1/users")
@SecurityScheme(
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)
@ApplicationScoped
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @RolesAllowed({"user", "admin"})
    @Path("/{id}")
    @Operation(summary = "Gets a user", description = "Retrieves a user by id")
    public User getUser(@PathParam("id") int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @Path("/save")
    @POST
    @RolesAllowed("admin")
    @Operation(summary = "Adds a user", description = "Creates a user and persists into database")
    public User createUser(UserDTO userDto) {
        return userService.saveUser(userDto);
    }

    @Path("/filter")
    @RolesAllowed("admin")
    @POST
    @Operation(summary = "Filter api", description = "Filter from user list")
    public List<UserDTO> filter(FilterRequestDTO filterRequestDTO) {

        return userService.filter(filterRequestDTO);
    }

    @Path("/login/{username}/{password}")
    @GET
    @PermitAll
    @Operation(summary = "login of user", description = "generate token")
    public String  loginUser(@PathParam("username") String username,
                             @PathParam("password") String password) throws IOException {
        return userService.loginUser(username,password);
    }

}
