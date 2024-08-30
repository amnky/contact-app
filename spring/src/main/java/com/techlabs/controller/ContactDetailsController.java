package com.techlabs.controller;

import com.techlabs.dto.ContactDetailsDTO;
import com.techlabs.dto.ContactDetailsResponseDTO;
import com.techlabs.service.ContactDetailsService;
import com.techlabs.utils.PagedResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts-details")
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    public ContactDetailsController(ContactDetailsService contactDetailsService) {
        this.contactDetailsService = contactDetailsService;
    }

    @PostMapping
    ResponseEntity<ContactDetailsResponseDTO>createContactDetails(
            @RequestBody @Valid ContactDetailsDTO contactDetailsDTO){
        ContactDetailsResponseDTO contactDetailsResponseDTO=contactDetailsService.createContactDetails(contactDetailsDTO);
        return new ResponseEntity<>(contactDetailsResponseDTO,HttpStatus.OK);
    }
    @GetMapping("/contacts/{cid}/details")
    ResponseEntity<PagedResponse<ContactDetailsResponseDTO>>getAllContactDetails(
            @PathVariable("cid") int cid,
            @RequestParam(name="pageNo",defaultValue = "0") int pageNo,
            @RequestParam(name="size",defaultValue = "10") int size,
            @RequestParam(name="sort",defaultValue = "ASC") String sort,
            @RequestParam(name="sortBy",defaultValue = "contactDetailsId") String sortBy,
            @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection){
        PagedResponse<ContactDetailsResponseDTO> contactDetailsResponseDTO=contactDetailsService.getContactDetails(cid,pageNo,size,sort,sortBy,sortDirection);
        return new ResponseEntity<>(contactDetailsResponseDTO,HttpStatus.OK);
    }
    @GetMapping("/{cid}")
    ResponseEntity<ContactDetailsResponseDTO>getContactDetailsById(@PathVariable("cid")int cid){
        ContactDetailsResponseDTO contactDetailsResponseDTO=contactDetailsService.getContactDetailsById(cid);
        return new ResponseEntity<>(contactDetailsResponseDTO,HttpStatus.OK);
    }
    @PutMapping("/{cid}")
    ResponseEntity<ContactDetailsResponseDTO>updateContactDetailsById(
            @PathVariable("cid")int cid,@RequestBody @Valid ContactDetailsDTO contactDetailsDTO){
        ContactDetailsResponseDTO contactDetailsResponseDTO=contactDetailsService.updateContactDetailsById(cid,contactDetailsDTO);
        return new ResponseEntity<>(contactDetailsResponseDTO,HttpStatus.OK);
    }
    @DeleteMapping("/{cid}")
    ResponseEntity<HttpStatus>deleteContactDetailsById(@PathVariable("cid")int cid){
        contactDetailsService.deleteContactDetailsById(cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
