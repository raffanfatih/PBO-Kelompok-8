package com.kostify20.kostify2.repository;

import com.kostify20.kostify2.model.Keluhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KeluhanRepository extends JpaRepository<Keluhan, String> {
    List<Keluhan> findByStatusPenanganan(String statusPenanganan);
    long countByStatusPenanganan(String statusPenanganan);
}
