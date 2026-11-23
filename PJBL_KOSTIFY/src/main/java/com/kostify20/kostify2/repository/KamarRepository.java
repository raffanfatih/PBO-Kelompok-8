package com.kostify20.kostify2.repository;

import com.kostify20.kostify2.model.Kamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KamarRepository extends JpaRepository<Kamar, String> {
    List<Kamar> findByStatusKamar(String statusKamar);
    long countByStatusKamar(String statusKamar);
}