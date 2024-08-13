package com.techlabs.service;

import com.techlabs.dto.ContactDTO;
import com.techlabs.dto.ContactResponseDTO;
import com.techlabs.utils.PagedResponse;

public interface ContactService {
    ContactResponseDTO addContact(ContactDTO contactDTO);

    void deleteContact(int id);

    ContactResponseDTO getContactById(int id);

    PagedResponse<ContactResponseDTO> getAllContacts(int pageNo, int size, String sort, String sortBy, String sortDirection);

    ContactResponseDTO updateContact(int id, ContactDTO contactDTO);
}
