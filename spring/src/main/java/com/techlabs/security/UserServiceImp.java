package com.techlabs.security;

import com.techlabs.dto.UserDTO;
import com.techlabs.dto.UserResponseDTO;
import com.techlabs.utils.PagedResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserResponseDTO getUserByID(int id) {
        return null;
    }

    @Override
    public PagedResponse<UserResponseDTO> getAllUsers(int pageNo, int size, String sort, String sortBy, String sortDirection) {
        return null;
    }
}
