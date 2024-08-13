package com.techlabs.service;

import com.techlabs.dto.UserDTO;
import com.techlabs.dto.UserResponseDTO;
import com.techlabs.utils.PagedResponse;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDTO);

    UserResponseDTO getUserByID(int id);

    PagedResponse<UserResponseDTO> getAllUsers(int pageNo, int size, String sort, String sortBy, String sortDirection);

    void deleteUserById(int id);
}
