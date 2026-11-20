package com.kostify20.kostify2.repository;

import com.kostify20.kostify2.model.Pembayaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PembayaranRepository extends JpaRepository<Pembayaran, String> {
    List<Pembayaran> findByStatusPembayaran(String statusPembayaran);
    long countByStatusPembayaran(String statusPembayaran);

    @Query("SELECT COALESCE(SUM(p.jumlahDibayar), 0) FROM Pembayaran p WHERE p.statusPembayaran = 'Lunas'")
    Long totalPembayaranDiterima();

    @Query("SELECT COALESCE(SUM(p.jumlahTagihan), 0) FROM Pembayaran p WHERE p.statusPembayaran = 'Belum Lunas'")
    Long totalBelumDibayar();

    @Query("SELECT COALESCE(SUM(p.jumlahTagihan), 0) FROM Pembayaran p WHERE p.statusPembayaran = 'Terlambat'")
    Long totalTerlambat();
}