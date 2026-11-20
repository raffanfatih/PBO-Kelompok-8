package com.kostify20.kostify2.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pembayaran")
public class Pembayaran {

    @Id
    @Column(name = "id_pembayaran", length = 10, nullable = false)
    private String idPembayaran;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kontrak", referencedColumnName = "id_kontrak")
    private Kontrak kontrak;

    @Column(name = "periode_bulan")
    private LocalDate periodeBulan;

    @Column(name = "tanggal_jatuh_tempo")
    private LocalDate tanggalJatuhTempo;

    @Column(name = "jumlah_tagihan")
    private Integer jumlahTagihan;

    @Column(name = "jumlah_dibayar")
    private Integer jumlahDibayar;

    @Column(name = "tanggal_bayar")
    private LocalDate tanggalBayar;

    @Column(name = "status_pembayaran", length = 30)
    private String statusPembayaran; // Lunas, Belum Lunas, Terlambat

    public Pembayaran() {}

    // Getters & Setters
    public String getIdPembayaran() { return idPembayaran; }
    public void setIdPembayaran(String idPembayaran) { this.idPembayaran = idPembayaran; }

    public Kontrak getKontrak() { return kontrak; }
    public void setKontrak(Kontrak kontrak) { this.kontrak = kontrak; }

    public LocalDate getPeriodeBulan() { return periodeBulan; }
    public void setPeriodeBulan(LocalDate periodeBulan) { this.periodeBulan = periodeBulan; }

    public LocalDate getTanggalJatuhTempo() { return tanggalJatuhTempo; }
    public void setTanggalJatuhTempo(LocalDate tanggalJatuhTempo) { this.tanggalJatuhTempo = tanggalJatuhTempo; }

    public Integer getJumlahTagihan() { return jumlahTagihan; }
    public void setJumlahTagihan(Integer jumlahTagihan) { this.jumlahTagihan = jumlahTagihan; }

    public Integer getJumlahDibayar() { return jumlahDibayar; }
    public void setJumlahDibayar(Integer jumlahDibayar) { this.jumlahDibayar = jumlahDibayar; }

    public LocalDate getTanggalBayar() { return tanggalBayar; }
    public void setTanggalBayar(LocalDate tanggalBayar) { this.tanggalBayar = tanggalBayar; }

    public String getStatusPembayaran() { return statusPembayaran; }
    public void setStatusPembayaran(String statusPembayaran) { this.statusPembayaran = statusPembayaran; }
}
