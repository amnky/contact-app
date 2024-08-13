package com.techlabs.repository;

import com.techlabs.entity.ContactDetails;
import com.techlabs.entity.Contacts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Integer> {
    Page<ContactDetails> findByContacts(Contacts contacts, Pageable pageable);
}
