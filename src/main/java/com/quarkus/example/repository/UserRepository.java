package com.quarkus.example.repository;

import com.quarkus.example.dto.FilterRequestDTO;
import com.quarkus.example.dto.UserDTO;
import com.quarkus.example.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Page;

import java.util.stream.Collectors;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {


    public List<UserDTO> filter(FilterRequestDTO filterRequestDTO) {
        String sortByColumn = filterRequestDTO.getSortBy();
        String sortDirection = filterRequestDTO.getSortDirection();

        String filterValue = filterRequestDTO.getFilterValue();
        String filterColumn = filterRequestDTO.getFilterColumn();

        Integer pageNo = filterRequestDTO.getPageNo();
        Integer pageSize = filterRequestDTO.getPageSize();

        Sort sort = null;
        if (sortByColumn != null && sortDirection != null) {
            sort = Sort.by(sortByColumn).direction(Sort.Direction.valueOf(sortDirection.toUpperCase()));
        }

        List<User> results;
        if (filterColumn != null && filterValue != null) {
            results = list(filterColumn + " = ?1", sort, filterValue);
        } else {
            results = listAll(sort);
        }

        if (pageNo != null && pageSize != null) {
            results = find(filterColumn + " = ?1", sort, filterValue).page(Page.of(pageNo, pageSize)).list();
        }

        return results.stream()
                .map(this::mapUserToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapUserToDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getUsername());
    }
}


