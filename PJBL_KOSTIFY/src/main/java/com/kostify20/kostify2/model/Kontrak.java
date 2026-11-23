package com.kostify20.kostify2.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "kontrak")
public class Kontrak {

    @Id
    @Column(name = "id_kontrak", length = 10, nullable = false)
    private String idKontrak;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kamar", referencedColumnName = "id_kamar")
    private Kamar kamar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_penghuni", referencedColumnName = "id_penghuni")
    private Penghuni penghuni;

    @Column(name = "tanggal_mulai")
    private LocalDate tanggalMulai;

    @Column(name = "tanggal_selesai")
    private LocalDate tanggalSelesai;

    @Column(name = "harga_sewa")
    private Integer hargaSewa;

    @Column(name = "tanggal_jatuh_tempo")
    private LocalDate tanggalJatuhTempo;

    @Column(name = "status_kontrak", length = 30)
    private String statusKontrak; // Aktif, Akan Berakhir, Berakhir

    public Kontrak() {}

    // Getters & Setters
    public String getIdKontrak() { return idKontrak; }
    public void setIdKontrak(String idKontrak) { this.idKontrak = idKontrak; }

    public Kamar getKamar() { return kamar; }
    public void setKamar(Kamar kamar) { this.kamar = kamar; }

    public Penghuni getPenghuni() { return penghuni; }
    public void setPenghuni(Penghuni penghuni) { this.penghuni = penghuni; }

    public LocalDate getTanggalMulai() { return tanggalMulai; }
    public void setTanggalMulai(LocalDate tanggalMulai) { this.tanggalMulai = tanggalMulai; }

    public LocalDate getTanggalSelesai() { return tanggalSelesai; }
    public void setTanggalSelesai(LocalDate tanggalSelesai) { this.tanggalSelesai = tanggalSelesai; }

    public Integer getHargaSewa() { return hargaSewa; }
    public void setHargaSewa(Integer hargaSewa) { this.hargaSewa = hargaSewa; }

    public LocalDate getTanggalJatuhTempo() { return tanggalJatuhTempo; }
    public void setTanggalJatuhTempo(LocalDate tanggalJatuhTempo) { this.tanggalJatuhTempo = tanggalJatuhTempo; }

    public String getStatusKontrak() { return statusKontrak; }
    public void setStatusKontrak(String statusKontrak) { this.statusKontrak = statusKontrak; }
}
