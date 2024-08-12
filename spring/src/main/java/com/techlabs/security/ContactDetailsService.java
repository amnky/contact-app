package com.techlabs.security;

import com.techlabs.dto.ContactDetailsDTO;
import com.techlabs.dto.ContactDetailsResponseDTO;
import com.techlabs.utils.PagedResponse;

public interface ContactDetailsService {
    ContactDetailsResponseDTO createContactDetails(int cid, ContactDetailsDTO contactDetailsDTO);

    PagedResponse<ContactDetailsResponseDTO> getContactDetails(int cid, int pageNo, int size, String sort, String sortBy, String sortDirection);

    ContactDetailsResponseDTO getContactDetailsById(int cid);

    ContactDetailsResponseDTO updateContactDetailsById(int cid);

    ContactDetailsResponseDTO deleteContactDetailsById(int cid);
}
