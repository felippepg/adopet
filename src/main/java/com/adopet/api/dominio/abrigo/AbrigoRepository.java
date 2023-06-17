package com.adopet.api.dominio.abrigo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    @Override
    Page<Abrigo> findAll(Pageable pageable);
}
