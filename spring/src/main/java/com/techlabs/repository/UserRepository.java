package com.techlabs.repository;

import com.techlabs.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.isActive = false WHERE u.userId = :userId")
    void inactivateUser(@Param("userId") int userId);

    Page<Users> findByIsActiveTrue(Pageable pageable);
}
