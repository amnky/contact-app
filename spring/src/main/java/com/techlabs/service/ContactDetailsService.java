package com.techlabs.service;

import com.techlabs.dto.ContactDetailsDTO;
import com.techlabs.dto.ContactDetailsResponseDTO;
import com.techlabs.utils.PagedResponse;

public interface ContactDetailsService {
    ContactDetailsResponseDTO createContactDetails( ContactDetailsDTO contactDetailsDTO);

    PagedResponse<ContactDetailsResponseDTO> getContactDetails(int cid, int pageNo, int size, String sort, String sortBy, String sortDirection);

    ContactDetailsResponseDTO getContactDetailsById(int cid);

    ContactDetailsResponseDTO updateContactDetailsById(int cid, ContactDetailsDTO contactDetailsDTO);

    void deleteContactDetailsById(int cid);
}
