package com.adopet.api.dominio.tutores;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Page<Tutor> findAll(Pageable pageable);
}
