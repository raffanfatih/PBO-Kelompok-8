package com.kostify20.kostify2.repository;

import com.kostify20.kostify2.model.Penghuni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenghuniRepository extends JpaRepository<Penghuni, String> {
}
