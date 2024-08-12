package com.techlabs.security;

import com.techlabs.dto.ContactDTO;
import com.techlabs.dto.ContactResponseDTO;
import com.techlabs.utils.PagedResponse;

public interface ContactService {
    ContactResponseDTO addContact(ContactDTO contactDTO);

    ContactResponseDTO deleteContact(int id);

    ContactResponseDTO getContactById(int id);

    PagedResponse<ContactResponseDTO> getAllContacts(int pageNo, int size, String sort, String sortBy, String sortDirection);

    ContactResponseDTO updateContact(int id);
}
