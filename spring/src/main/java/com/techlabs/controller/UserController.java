package com.techlabs.controller;

import com.techlabs.dto.UserDTO;
import com.techlabs.dto.UserResponseDTO;
import com.techlabs.service.UserService;
import com.techlabs.utils.PagedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    ResponseEntity<UserResponseDTO>createUser(UserDTO userDTO){
        UserResponseDTO userResponseDTO=userService.createUser(userDTO);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<UserResponseDTO>getUserByID(@PathVariable("id") int id){
        UserResponseDTO userResponseDTO=userService.getUserByID(id);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<PagedResponse<UserResponseDTO>> getAllUsers(
                                                               @RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                                               @RequestParam(name="size",defaultValue = "10") int size,
                                                               @RequestParam(name="sort",defaultValue = "ASC") String sort,
                                                               @RequestParam(name="sortBy",defaultValue = "transactionTime") String sortBy,
                                                               @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection){
        PagedResponse<UserResponseDTO> userResponseDTO=userService.getAllUsers(pageNo,size,sort,sortBy,sortDirection);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus>deleteUser(@PathVariable("id") int id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
