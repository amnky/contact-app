package com.techlabs.security;

import com.techlabs.dto.ContactDetailsDTO;
import com.techlabs.dto.ContactDetailsResponseDTO;
import com.techlabs.utils.PagedResponse;
import org.springframework.stereotype.Service;

@Service
public class ContactDetailsServiceImp implements ContactDetailsService{
    @Override
    public ContactDetailsResponseDTO createContactDetails(int cid, ContactDetailsDTO contactDetailsDTO) {
        return null;
    }

    @Override
    public PagedResponse<ContactDetailsResponseDTO> getContactDetails(int cid, int pageNo, int size, String sort, String sortBy, String sortDirection) {
        return null;
    }

    @Override
    public ContactDetailsResponseDTO getContactDetailsById(int cid) {
        return null;
    }

    @Override
    public ContactDetailsResponseDTO updateContactDetailsById(int cid) {
        return null;
    }

    @Override
    public ContactDetailsResponseDTO deleteContactDetailsById(int cid) {
        return null;
    }
}
