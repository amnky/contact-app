package com.techlabs.repository;

import com.techlabs.entity.Contacts;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contacts,Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Contacts c SET c.isActive = false WHERE c.contactId = :contactId")
    void inactivateContact(@Param("contactId") int id);

    Page<Contacts> findByIsActiveTrue(Pageable pageable);
}
