package com.kostify20.kostify2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kamar")
public class Kamar {

    @Id
    @Column(name = "id_kamar", length = 10, nullable = false)
    private String idKamar;

    @Column(name = "fasilitas", length = 255)
    private String fasilitas;

    @Column(name = "lokasi", length = 255)
    private String lokasi;

    @Column(name = "harga_bulanan")
    private Integer hargaBulanan;

    @Column(name = "status_kamar", length = 30)
    private String statusKamar; // Contoh: Tersedia, Terisi, Perbaikan

    @Column(name = "luas_sewaan")
    private Integer luasSewaan;

    @Column(name = "jumlah_kamar")
    private Integer jumlahKamar;

    public Kamar() {}

    // Getters & Setters
    public String getIdKamar() { return idKamar; }
    public void setIdKamar(String idKamar) { this.idKamar = idKamar; }

    public String getFasilitas() { return fasilitas; }
    public void setFasilitas(String fasilitas) { this.fasilitas = fasilitas; }

    public String getLokasi() { return lokasi; }
    public void setLokasi(String lokasi) { this.lokasi = lokasi; }

    public Integer getHargaBulanan() { return hargaBulanan; }
    public void setHargaBulanan(Integer hargaBulanan) { this.hargaBulanan = hargaBulanan; }

    public String getStatusKamar() { return statusKamar; }
    public void setStatusKamar(String statusKamar) { this.statusKamar = statusKamar; }

    public Integer getLuasSewaan() { return luasSewaan; }
    public void setLuasSewaan(Integer luasSewaan) { this.luasSewaan = luasSewaan; }

    public Integer getJumlahKamar() { return jumlahKamar; }
    public void setJumlahKamar(Integer jumlahKamar) { this.jumlahKamar = jumlahKamar; }
}
