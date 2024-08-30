package com.techlabs.repository;

import com.techlabs.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Credential,Integer> {
    Credential findByCustomerId(int customerId);
}
