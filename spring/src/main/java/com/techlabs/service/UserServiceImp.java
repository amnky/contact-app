package com.techlabs.service;

import com.techlabs.dto.UserDTO;
import com.techlabs.dto.UserResponseDTO;
import com.techlabs.entity.Users;
import com.techlabs.repository.UserRepository;
import com.techlabs.utils.PagedResponse;
import org.hibernate.annotations.NotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        Users user=userDtoToUser(userDTO);
        user=userRepository.save(user);
        return userToUserResponseDto(user);
    }


    @Override
    public UserResponseDTO getUserByID(int id) {
        Users user=userRepository.findById(id).filter(Users::getIsActive).
                orElseThrow(() -> new NoSuchElementException("user is not found"));
        return userToUserResponseDto(user);
    }

    @Override
    public PagedResponse<UserResponseDTO> getAllUsers(int pageNo, int size, String sort, String sortBy, String sortDirection) {
        Sort sorting = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, size,sorting);
        Page<Users> pagedUsers = userRepository.findByIsActiveTrue(pageable);
        List<Users> usersList = pagedUsers.getContent();
        List<UserResponseDTO> userResponseDTOList=usersListToUserResponseDtoList(usersList);
        return new PagedResponse<>(userResponseDTOList, pagedUsers.getNumber(),
                pagedUsers.getSize(), pagedUsers.getTotalElements(), pagedUsers.getTotalPages(),
                pagedUsers.isLast());
    }

    @Override
    public void deleteUserById(int id) {
//        just inactive the user
        userRepository.inactivateUser(id);
    }

    private List<UserResponseDTO> usersListToUserResponseDtoList(List<Users> usersList) {
        List<UserResponseDTO> userResponseDTOList=new ArrayList<>();
        for(Users users:usersList){
            userResponseDTOList.add(userToUserResponseDto(users));
        }
        return userResponseDTOList;
    }

//    private methods

    private UserResponseDTO userToUserResponseDto(Users user) {
        return new UserResponseDTO(user.getUserId(),user.getFirstName(),user.getLastName(),
                user.getIsAdmin());
    }

    private Users userDtoToUser(UserDTO userDTO) {
        return new Users(userDTO.getFirstName(),userDTO.getLastName(),userDTO.isAdmin(),true);
    }
}
