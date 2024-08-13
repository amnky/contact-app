package com.techlabs.service;

import com.techlabs.dto.ContactDTO;
import com.techlabs.dto.ContactResponseDTO;
import com.techlabs.entity.Contacts;
import com.techlabs.entity.Users;
import com.techlabs.repository.ContactRepository;
import com.techlabs.repository.UserRepository;
import com.techlabs.utils.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContactServiceImp implements ContactService{
    
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public ContactServiceImp(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ContactResponseDTO addContact(ContactDTO contactDTO) {
        Users users=userRepository.findById(contactDTO.getUserId()).
                orElseThrow(()->new NoSuchElementException("user not found for this contact"));
        Contacts contacts=contactDtoToContact(contactDTO,users);
        contacts=contactRepository.save(contacts);
        return contactsToContactResponseDto(contacts);
        
    }

    @Override
    public void deleteContact(int id) {
        contactRepository.inactivateContact(id);
    }

    @Override
    public ContactResponseDTO getContactById(int id) {

        Contacts contacts=contactRepository.findById(id).filter(Contacts::getIsActive).
                orElseThrow(()->new NoSuchElementException("contact not found"));
        return contactsToContactResponseDto(contacts);
    }

    @Override
    public PagedResponse<ContactResponseDTO> getAllContacts(int pageNo, int size, String sort,
                                                            String sortBy, String sortDirection) {
        Sort sorting = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, size,sorting);
        Page<Contacts> contactsPage = contactRepository.findByIsActiveTrue(pageable);
        List<Contacts> contactsList = contactsPage.getContent();
        List<ContactResponseDTO> userResponseDTOList=contactListToContactResponseDtoList(contactsList);
        return new PagedResponse<>(userResponseDTOList, contactsPage.getNumber(),
                contactsPage.getSize(), contactsPage.getTotalElements(), contactsPage.getTotalPages(),
                contactsPage.isLast());
    }



    @Override
    public ContactResponseDTO updateContact(int id, ContactDTO contactDTO) {
        Contacts oldContacts=contactDtoToContact(contactDTO);
        Contacts updatedContacts=contactRepository.save(oldContacts);
        return contactsToContactResponseDto(updatedContacts);
    }

    private ContactResponseDTO contactsToContactResponseDto(Contacts contacts) {
        return new ContactResponseDTO(contacts.getContactId(),contacts.getFirstName(),
                contacts.getLastName(),contacts.getIsActive());
    }

    private Contacts contactDtoToContact(ContactDTO contactDTO,Users users) {

        return new Contacts(contactDTO.getFirstName(),contactDTO.getLastName(),true,users);
    }
    private Contacts contactDtoToContact(ContactDTO contactDTO) {

        return new Contacts(contactDTO.getFirstName(),contactDTO.getLastName());
    }
    private List<ContactResponseDTO> contactListToContactResponseDtoList(List<Contacts> contactsList) {
        List<ContactResponseDTO> contactResponseDTOList=new ArrayList<>();
        for(Contacts contacts:contactsList){
            contactResponseDTOList.add(contactsToContactResponseDto(contacts));
        }
        return contactResponseDTOList;
    }
}
