package com.kostify20.kostify2.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "keluhan")
public class Keluhan {

    @Id
    @Column(name = "id_keluhan", length = 10, nullable = false)
    private String idKeluhan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kontrak", referencedColumnName = "id_kontrak")
    private Kontrak kontrak;

    @Column(name = "tanggal_keluhan")
    private LocalDate tanggalKeluhan;

    @Column(name = "kategori", length = 100)
    private String kategori; // Kebersihan, Air, Fasilitas, Listrik, Keamanan, Lainnya

    @Column(name = "deskripsi", columnDefinition = "TEXT")
    private String deskripsi;

    @Column(name = "status_penanganan", length = 30)
    private String statusPenanganan; // Menunggu, Diproses, Selesai

    @Column(name = "tanggal_selesai")
    private LocalDate tanggalSelesai;

    public Keluhan() {}

    // Getters & Setters
    public String getIdKeluhan() { return idKeluhan; }
    public void setIdKeluhan(String idKeluhan) { this.idKeluhan = idKeluhan; }

    public Kontrak getKontrak() { return kontrak; }
    public void setKontrak(Kontrak kontrak) { this.kontrak = kontrak; }

    public LocalDate getTanggalKeluhan() { return tanggalKeluhan; }
    public void setTanggalKeluhan(LocalDate tanggalKeluhan) { this.tanggalKeluhan = tanggalKeluhan; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getStatusPenanganan() { return statusPenanganan; }
    public void setStatusPenanganan(String statusPenanganan) { this.statusPenanganan = statusPenanganan; }

    public LocalDate getTanggalSelesai() { return tanggalSelesai; }
    public void setTanggalSelesai(LocalDate tanggalSelesai) { this.tanggalSelesai = tanggalSelesai; }
}
