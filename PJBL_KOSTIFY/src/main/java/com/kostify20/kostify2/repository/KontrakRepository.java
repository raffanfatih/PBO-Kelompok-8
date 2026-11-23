package com.kostify20.kostify2.repository;

import com.kostify20.kostify2.model.Kontrak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KontrakRepository extends JpaRepository<Kontrak, String> {
    List<Kontrak> findByStatusKontrak(String statusKontrak);
    long countByStatusKontrak(String statusKontrak);
}
