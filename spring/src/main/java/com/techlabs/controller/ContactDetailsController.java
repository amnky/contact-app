package com.techlabs.controller;

import com.techlabs.dto.ContactDetailsDTO;
import com.techlabs.dto.ContactDetailsResponseDTO;
import com.techlabs.service.ContactDetailsService;
import com.techlabs.utils.PagedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    public ContactDetailsController(ContactDetailsService contactDetailsService) {
        this.contactDetailsService = contactDetailsService;
    }
    @PostMapping("contacts-details")
    ResponseEntity<ContactDetailsResponseDTO>createContactDetails(
                                                                  @RequestBody ContactDetailsDTO contactDetailsDTO){
        ContactDetailsResponseDTO contactDetailsResponseDTO=contactDetailsService.createContactDetails(contactDetailsDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("contacts/{cid}/details")
    ResponseEntity<PagedResponse<ContactDetailsResponseDTO>>getContactDetails(@PathVariable("cid") int cid,
                                                                              @RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                                                              @RequestParam(name="size",defaultValue = "10") int size,
                                                                              @RequestParam(name="sort",defaultValue = "ASC") String sort,
                                                                              @RequestParam(name="sortBy",defaultValue = "transactionTime") String sortBy,
                                                                              @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection){
        PagedResponse<ContactDetailsResponseDTO> contactDetailsResponseDTO=contactDetailsService.getContactDetails(cid,pageNo,size,sort,sortBy,sortDirection);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/contact-details/{cid}")
    ResponseEntity<ContactDetailsResponseDTO>getContactDetailsById(@PathVariable("cid")int cid){
        ContactDetailsResponseDTO contactDetailsResponseDTO=contactDetailsService.getContactDetailsById(cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/contact-details/{cid}")
    ResponseEntity<ContactDetailsResponseDTO>updateContactDetailsById(@PathVariable("cid")int cid,@RequestBody
                                                                      ContactDetailsDTO contactDetailsDTO){
        ContactDetailsResponseDTO contactDetailsResponseDTO=contactDetailsService.updateContactDetailsById(cid,contactDetailsDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/contact-details/{cid}")
    ResponseEntity<HttpStatus>deleteContactDetailsById(@PathVariable("cid")int cid){
        contactDetailsService.deleteContactDetailsById(cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
