package com.techlabs.service;

import com.techlabs.dto.UserDTO;
import com.techlabs.dto.UserResponseDTO;
import com.techlabs.entity.Credential;
import com.techlabs.entity.Users;
import com.techlabs.exception.ContactApiException;
import com.techlabs.exception.UserException;
import com.techlabs.repository.AuthRepository;
import com.techlabs.repository.UserRepository;
import com.techlabs.utils.PagedResponse;
import org.hibernate.service.UnknownServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    public UserServiceImp(UserRepository userRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        checkAdminAccess();
        Users user=userDtoToUser(userDTO);
        user=userRepository.save(user);
        createCredentials(user);
        return userToUserResponseDto(user);
    }

    private void createCredentials(Users user) {
        Credential credential=new Credential();
//        if(admin)  credential.setRole(Credential.Role.ROLE_ADMIN);
        credential.setRole(Credential.Role.ROLE_STAFF);
        credential.setCustomerId(user.getUserId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(String.valueOf(user.getUserId()));
        credential.setPassword(encodedPassword);
        authRepository.save(credential);
    }


    @Override
    public UserResponseDTO getUserByID(int id) {
        checkAccess(id);
        Users user=userRepository.findById(id).filter(Users::isActive).
                orElseThrow(() -> new UserException("contact not found"));
        return userToUserResponseDto(user);
    }


    @Override
    public PagedResponse<UserResponseDTO> getAllUsers(int pageNo, int size, String sort, String sortBy, String sortDirection) {
        checkAdminAccess();
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
        checkAdminAccess();
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
                user.isAdmin());
    }

    private Users userDtoToUser(UserDTO userDTO) {
        return new Users(userDTO.getFirstName(),userDTO.getLastName(),false,true);
    }

    private void checkAdminAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (!hasUserRole) {
            System.out.println("you have not access");
            throw new ResourceAccessException("you haven't access to this resource, please contact admin");
        }
    }
    private void checkAccess(int customerId){
        String customerLoginId = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CUSTOMER"));
        if(!customerLoginId.equals(String.valueOf(customerId)) && hasUserRole){
            throw  new ResourceAccessException("you haven't access to this resource, please contact admin");
        }
    }

}
