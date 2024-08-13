package com.techlabs.controller;

import com.techlabs.dto.ContactDTO;
import com.techlabs.dto.ContactResponseDTO;
import com.techlabs.service.ContactService;
import com.techlabs.utils.PagedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private/api")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contacts")
    ResponseEntity<ContactResponseDTO>addContact(@RequestBody ContactDTO contactDTO){
        ContactResponseDTO contactResponseDTO=contactService.addContact(contactDTO);
        return new ResponseEntity<>(contactResponseDTO,HttpStatus.OK);
    }
    @DeleteMapping("/contacts/{id}")
    ResponseEntity<HttpStatus>deleteContact(@PathVariable("id")int id){
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/contacts/{id}")
    ResponseEntity<ContactResponseDTO>getContactById(@PathVariable("id")int id){
        ContactResponseDTO contactResponseDTO=contactService.getContactById(id);
        return new ResponseEntity<>(contactResponseDTO,HttpStatus.OK);
    }
    @PutMapping("/contacts/{id}")
    ResponseEntity<ContactResponseDTO>updateContact(@PathVariable("id")int id,@RequestBody ContactDTO contactDTO){
        ContactResponseDTO contactResponseDTO=contactService.updateContact(id,contactDTO);
        return new ResponseEntity<>(contactResponseDTO,HttpStatus.OK);
    }
    @GetMapping("/contacts")
    ResponseEntity<PagedResponse<ContactResponseDTO>> getAllContacts(
                                                                     @RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                                                     @RequestParam(name="size",defaultValue = "10") int size,
                                                                     @RequestParam(name="sort",defaultValue = "ASC") String sort,
                                                                     @RequestParam(name="sortBy",defaultValue = "transactionTime") String sortBy,
                                                                     @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection){
        PagedResponse<ContactResponseDTO> contactResponseDTO=contactService.getAllContacts(pageNo,size,sort,sortBy,sortDirection);
        return new ResponseEntity<>(contactResponseDTO,HttpStatus.OK);
    }
}
