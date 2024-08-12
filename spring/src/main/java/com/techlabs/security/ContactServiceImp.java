package com.techlabs.security;

import com.techlabs.dto.ContactDTO;
import com.techlabs.dto.ContactResponseDTO;
import com.techlabs.utils.PagedResponse;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImp implements ContactService{
    @Override
    public ContactResponseDTO addContact(ContactDTO contactDTO) {
        return null;
    }

    @Override
    public ContactResponseDTO deleteContact(int id) {
        return null;
    }

    @Override
    public ContactResponseDTO getContactById(int id) {
        return null;
    }

    @Override
    public PagedResponse<ContactResponseDTO> getAllContacts(int pageNo, int size, String sort, String sortBy, String sortDirection) {
        return null;
    }

    @Override
    public ContactResponseDTO updateContact(int id) {
        return null;
    }
}
