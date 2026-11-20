package com.kostify20.kostify2.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "penghuni")
public class Penghuni {

    @Id
    @Column(name = "id_penghuni", length = 10, nullable = false)
    private String idPenghuni;

    @Column(name = "nama", length = 100)
    private String nama;

    @Column(name = "no_telepon", length = 20)
    private String noTelepon;

    @Column(name = "pekerjaan", length = 100)
    private String pekerjaan;

    @Column(name = "tanggal_daftar")
    private LocalDate tanggalDaftar;

    @Column(name = "jenis_kelamin", length = 20)
    private String jenisKelamin;

    public Penghuni() {}

    // Getters & Setters
    public String getIdPenghuni() { return idPenghuni; }
    public void setIdPenghuni(String idPenghuni) { this.idPenghuni = idPenghuni; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }

    public String getPekerjaan() { return pekerjaan; }
    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }

    public LocalDate getTanggalDaftar() { return tanggalDaftar; }
    public void setTanggalDaftar(LocalDate tanggalDaftar) { this.tanggalDaftar = tanggalDaftar; }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
}
