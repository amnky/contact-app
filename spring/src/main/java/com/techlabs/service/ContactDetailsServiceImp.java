package com.techlabs.service;

import com.techlabs.dto.ContactDetailsDTO;
import com.techlabs.dto.ContactDetailsResponseDTO;
import com.techlabs.entity.ContactDetails;
import com.techlabs.entity.Contacts;
import com.techlabs.repository.ContactDetailsRepository;
import com.techlabs.repository.ContactRepository;
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
public class ContactDetailsServiceImp implements ContactDetailsService{

    private final ContactDetailsRepository contactDetailsRepository;
    private final ContactRepository contactRepository;

    public ContactDetailsServiceImp(ContactDetailsRepository contactDetailsRepository,
                                    ContactRepository contactRepository) {
        this.contactDetailsRepository = contactDetailsRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactDetailsResponseDTO createContactDetails(ContactDetailsDTO contactDetailsDTO) {
        Contacts contacts=contactRepository.findById(contactDetailsDTO.getContactId()).
                filter(Contacts::getIsActive).orElseThrow(()-> new NoSuchElementException("contact not found"));
        ContactDetails contactDetails=contactDetailsDtoToContactDetails(contactDetailsDTO,contacts);
        contactDetails=contactDetailsRepository.save(contactDetails);
        return contactDetailsToResponseDto(contactDetails);
    }

    @Override
    public PagedResponse<ContactDetailsResponseDTO> getContactDetails(int cid, int pageNo, int size,
                                                                      String sort, String sortBy,
                                                                      String sortDirection) {
        Sort sorting = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, size,sorting);
        Contacts contacts=contactRepository.findById(cid).orElseThrow(()->new NoSuchElementException("contact not found"));
        Page<ContactDetails> contactDetailsPage = contactDetailsRepository.findByContacts(contacts,pageable);
        List<ContactDetails> contactDetailsList = contactDetailsPage.getContent();
        List<ContactDetailsResponseDTO> contactDetailListToResponseDto = contactDetailListToResponseDto(contactDetailsList);
        return new PagedResponse<>(contactDetailListToResponseDto, contactDetailsPage.getNumber(),
                contactDetailsPage.getSize(), contactDetailsPage.getTotalElements(), contactDetailsPage.getTotalPages(),
                contactDetailsPage.isLast());
    }

    private List<ContactDetailsResponseDTO> contactDetailListToResponseDto(List<ContactDetails> contactDetailsList) {
        List<ContactDetailsResponseDTO> contactDetailsResponseDTOList=new ArrayList<>();
        for(ContactDetails contactDetails:contactDetailsList){
            contactDetailsResponseDTOList.add(contactDetailsToResponseDto(contactDetails));
        }
        return contactDetailsResponseDTOList;
    }

    @Override
    public ContactDetailsResponseDTO getContactDetailsById(int cid) {
        ContactDetails contactDetails=contactDetailsRepository.findById(cid).orElseThrow(()->
                new NoSuchElementException("contact details not found"));
        return contactDetailsToResponseDto(contactDetails);
    }

    @Override
    public ContactDetailsResponseDTO updateContactDetailsById(int cid, ContactDetailsDTO contactDetailsDTO) {
        ContactDetails contactDetails=contactDetailsRepository.findById(cid).orElseThrow(()->
                new NoSuchElementException("contact details not found"));
        contactDetails.setEmail(contactDetailsDTO.getEmail());
        contactDetails.setMobileNo(contactDetails.getMobileNo());
        contactDetails=contactDetailsRepository.save(contactDetails);
        return contactDetailsToResponseDto(contactDetails);
    }

    @Override
    public void deleteContactDetailsById(int cid) {
        contactDetailsRepository.deleteById(cid);
    }

    private ContactDetailsResponseDTO contactDetailsToResponseDto(ContactDetails contactDetails) {
        return new ContactDetailsResponseDTO(contactDetails.getEmail(),contactDetails.getMobileNo());
    }

    private ContactDetails contactDetailsDtoToContactDetails(ContactDetailsDTO contactDetailsDTO,
                                                             Contacts contacts) {
        return new ContactDetails(contactDetailsDTO.getEmail(),
                contactDetailsDTO.getMobileNo(),contacts);
    }
}
