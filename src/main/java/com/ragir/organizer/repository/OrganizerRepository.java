package com.ragir.organizer.repository;

import com.ragir.organizer.model.entity.Organizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Page<Organizer> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContaining(
            String name, String email, String phone, Pageable pageable
    );

}
