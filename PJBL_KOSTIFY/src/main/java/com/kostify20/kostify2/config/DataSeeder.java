package com.kostify20.kostify2.config;


import com.kostify20.kostify2.model.*;
import com.kostify20.kostify2.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final KamarRepository kamarRepo;
    private final PenghuniRepository penghuniRepo;
    private final KontrakRepository kontrakRepo;
    private final PembayaranRepository pembayaranRepo;
    private final KeluhanRepository keluhanRepo;

    public DataSeeder(KamarRepository kamarRepo,
                      PenghuniRepository penghuniRepo,
                      KontrakRepository kontrakRepo,
                      PembayaranRepository pembayaranRepo,
                      KeluhanRepository keluhanRepo) {
        this.kamarRepo = kamarRepo;
        this.penghuniRepo = penghuniRepo;
        this.kontrakRepo = kontrakRepo;
        this.pembayaranRepo = pembayaranRepo;
        this.keluhanRepo = keluhanRepo;
    }

    @Override
    public void run(String... args) {
        if (kamarRepo.count() == 0) {
            seedInitialData();
        }
    }

    public void resetData() {
        keluhanRepo.deleteAll();
        pembayaranRepo.deleteAll();
        kontrakRepo.deleteAll();
        penghuniRepo.deleteAll();
        kamarRepo.deleteAll();
        seedInitialData();
    }

    public void seedInitialData() {
        // 1. Seed 12 Kamar (A01-A05, B01-B04, C01-C03)
        Kamar a01 = createKamar("A01", "Kasur, Lemari, Meja Belajar, Wi-Fi", "Lantai 1 - Sayap A", 1300000, "Terisi", 12, 1);
        Kamar a02 = createKamar("A02", "Kasur, Lemari, Meja Belajar, Wi-Fi", "Lantai 1 - Sayap A", 1300000, "Terisi", 12, 1);
        Kamar a03 = createKamar("A03", "Kasur, Lemari, Meja Belajar, Wi-Fi", "Lantai 1 - Sayap A", 1300000, "Terisi", 12, 1);
        Kamar a04 = createKamar("A04", "Kasur, Lemari, Meja Belajar, Wi-Fi", "Lantai 1 - Sayap A", 1300000, "Terisi", 12, 1);
        Kamar a05 = createKamar("A05", "Kasur, Lemari, Meja Belajar, Wi-Fi", "Lantai 1 - Sayap A", 1300000, "Tersedia", 12, 1);

        Kamar b01 = createKamar("B01", "AC, Kamar Mandi Dalam, Kasur Queen, Wi-Fi", "Lantai 2 - Sayap B", 1800000, "Terisi", 16, 1);
        Kamar b02 = createKamar("B02", "AC, Kamar Mandi Dalam, Kasur Queen, Wi-Fi", "Lantai 2 - Sayap B", 1800000, "Terisi", 16, 1);
        Kamar b03 = createKamar("B03", "AC, Kamar Mandi Dalam, Kasur Queen, Wi-Fi", "Lantai 2 - Sayap B", 1800000, "Terisi", 16, 1);
        Kamar b04 = createKamar("B04", "AC, Kamar Mandi Dalam, Kasur Queen, Wi-Fi", "Lantai 2 - Sayap B", 1800000, "Terisi", 16, 1);

        Kamar c01 = createKamar("C01", "AC, Water Heater, Balkon, Smart TV, Wi-Fi", "Lantai 3 - VIP", 2500000, "Terisi", 22, 1);
        Kamar c02 = createKamar("C02", "AC, Water Heater, Balkon, Smart TV, Wi-Fi", "Lantai 3 - VIP", 2500000, "Tersedia", 22, 1);
        Kamar c03 = createKamar("C03", "AC, Water Heater, Balkon, Smart TV, Wi-Fi", "Lantai 3 - VIP", 2500000, "Perbaikan", 22, 1);

        kamarRepo.saveAll(List.of(a01, a02, a03, a04, a05, b01, b02, b03, b04, c01, c02, c03));

        // 2. Seed 10 Penghuni (sesuai screenshot Manajemen Penghuni & Kontrak)
        Penghuni p1 = createPenghuni("PGH001", "Rizky Pratama", "0812-3456-7801", "Mahasiswa", LocalDate.of(2025, 11, 22), "Laki-laki");
        Penghuni p2 = createPenghuni("PGH002", "Siti Nurhaliza", "0813-2211-4502", "Mahasiswi", LocalDate.of(2026, 6, 5), "Perempuan");
        Penghuni p3 = createPenghuni("PGH003", "Andi Wijaya", "0857-1020-3303", "Karyawan Swasta", LocalDate.of(2026, 2, 8), "Laki-laki");
        Penghuni p4 = createPenghuni("PGH004", "Dewi Lestari", "0821-9988-7604", "Desainer Grafis", LocalDate.of(2026, 4, 9), "Perempuan");
        Penghuni p5 = createPenghuni("PGH005", "Budi Santoso", "0878-4455-6605", "Programmer", LocalDate.of(2026, 5, 29), "Laki-laki");
        Penghuni p6 = createPenghuni("PGH006", "Maya Anggraini", "0852-3344-5506", "Akuntan", LocalDate.of(2026, 7, 28), "Perempuan");
        Penghuni p7 = createPenghuni("PGH007", "Fajar Ramadhan", "0896-7788-9907", "Mahasiswa", LocalDate.of(2026, 9, 26), "Laki-laki");
        Penghuni p8 = createPenghuni("PGH008", "Putri Amelia", "0811-2233-4408", "Mahasiswi", LocalDate.of(2026, 6, 25), "Perempuan");
        Penghuni p9 = createPenghuni("PGH009", "Hendra Kusuma", "0819-5566-7709", "Dosen", LocalDate.of(2025, 12, 2), "Laki-laki");
        Penghuni p10 = createPenghuni("PGH010", "Nadia Safitri", "0838-1212-3410", "Alumni", LocalDate.of(2026, 4, 21), "Perempuan");

        penghuniRepo.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

        // 3. Seed 10 Kontrak (sesuai screenshot Manajemen Kontrak)
        Kontrak k1 = createKontrak("KTR001", a05, p10, LocalDate.of(2026, 4, 21), LocalDate.of(2026, 10, 21), 1300000, LocalDate.of(2026, 10, 5), "Berakhir");
        Kontrak k2 = createKontrak("KTR002", a01, p1, LocalDate.of(2025, 11, 22), LocalDate.of(2026, 11, 22), 1300000, LocalDate.of(2026, 11, 5), "Akan Berakhir");
        Kontrak k3 = createKontrak("KTR003", c01, p9, LocalDate.of(2025, 12, 2), LocalDate.of(2026, 12, 2), 2500000, LocalDate.of(2026, 11, 5), "Akan Berakhir");
        Kontrak k4 = createKontrak("KTR004", a02, p2, LocalDate.of(2026, 6, 5), LocalDate.of(2026, 12, 5), 1300000, LocalDate.of(2026, 11, 5), "Akan Berakhir");
        Kontrak k5 = createKontrak("KTR005", b04, p8, LocalDate.of(2026, 6, 25), LocalDate.of(2026, 12, 25), 1800000, LocalDate.of(2026, 11, 5), "Aktif");
        Kontrak k6 = createKontrak("KTR006", a03, p3, LocalDate.of(2026, 2, 8), LocalDate.of(2027, 2, 8), 1300000, LocalDate.of(2026, 11, 5), "Aktif");
        Kontrak k7 = createKontrak("KTR007", a04, p4, LocalDate.of(2026, 4, 9), LocalDate.of(2027, 4, 9), 1300000, LocalDate.of(2026, 11, 5), "Aktif");
        Kontrak k8 = createKontrak("KTR008", b01, p5, LocalDate.of(2026, 5, 29), LocalDate.of(2027, 5, 29), 1800000, LocalDate.of(2026, 11, 5), "Aktif");
        Kontrak k9 = createKontrak("KTR009", b02, p6, LocalDate.of(2026, 7, 28), LocalDate.of(2027, 7, 28), 1800000, LocalDate.of(2026, 11, 5), "Aktif");
        Kontrak k10 = createKontrak("KTR010", b03, p7, LocalDate.of(2026, 9, 26), LocalDate.of(2027, 9, 26), 1800000, LocalDate.of(2026, 11, 5), "Aktif");

        kontrakRepo.saveAll(List.of(k1, k2, k3, k4, k5, k6, k7, k8, k9, k10));

        // 4. Seed Pembayaran (sesuai screenshot Manajemen Pembayaran)
        Pembayaran pb1 = createPembayaran("BYR001", k6, LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 5), 1300000, 1300000, LocalDate.of(2026, 11, 5), "Lunas");
        Pembayaran pb2 = createPembayaran("BYR002", k8, LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 5), 1800000, 1800000, LocalDate.of(2026, 11, 5), "Lunas");
        Pembayaran pb3 = createPembayaran("BYR003", k7, LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 5), 1300000, 0, null, "Terlambat");
        Pembayaran pb4 = createPembayaran("BYR004", k10, LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 5), 1800000, 0, null, "Terlambat");
        Pembayaran pb5 = createPembayaran("BYR005", k3, LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 5), 2500000, 2500000, LocalDate.of(2026, 11, 5), "Lunas");
        Pembayaran pb6 = createPembayaran("BYR006", k9, LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 5), 1800000, 1800000, LocalDate.of(2026, 11, 4), "Lunas");
        Pembayaran pb7 = createPembayaran("BYR007", k5, LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 5), 1800000, 1800000, LocalDate.of(2026, 11, 4), "Lunas");
        Pembayaran pb8 = createPembayaran("BYR008", k2, LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 5), 1300000, 0, null, "Terlambat");
        Pembayaran pb9 = createPembayaran("BYR009", k4, LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 5), 1300000, 1300000, LocalDate.of(2026, 11, 4), "Lunas");
        Pembayaran pb10 = createPembayaran("BYR010", k6, LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 5), 1300000, 1300000, LocalDate.of(2026, 10, 5), "Lunas");

        pembayaranRepo.saveAll(List.of(pb1, pb2, pb3, pb4, pb5, pb6, pb7, pb8, pb9, pb10));

        // 5. Seed 8 Keluhan (sesuai screenshot Manajemen Keluhan)
        Keluhan kl1 = createKeluhan("KLH001", k6, LocalDate.of(2026, 11, 10), "Kebersihan", "Terdapat banyak semut di sekitar dapur bersama, mohon dilakukan penyemprotan.", "Menunggu", null);
        Keluhan kl2 = createKeluhan("KLH002", k4, LocalDate.of(2026, 11, 9), "Air", "Air keran di kamar mandi mengalir kecil sejak dua hari terakhir dan sering mati pada pagi hari.", "Menunggu", null);
        Keluhan kl3 = createKeluhan("KLH003", k8, LocalDate.of(2026, 11, 8), "Fasilitas", "AC di kamar tidak dingin dan mengeluarkan bunyi berdengung saat dinyalakan.", "Diproses", null);
        Keluhan kl4 = createKeluhan("KLH004", k10, LocalDate.of(2026, 11, 7), "Listrik", "Stopkontak dekat meja belajar sering korslet dan mati sendiri.", "Diproses", null);
        Keluhan kl5 = createKeluhan("KLH005", k2, LocalDate.of(2026, 11, 5), "Kebersihan", "Lorong lantai satu belum dibersihkan selama tiga hari dan tempat sampah penuh.", "Selesai", LocalDate.of(2026, 11, 6));
        Keluhan kl6 = createKeluhan("KLH006", k7, LocalDate.of(2026, 11, 4), "Keamanan", "Lampu area parkir motor mati sehingga malam hari sangat gelap.", "Menunggu", null);
        Keluhan kl7 = createKeluhan("KLH007", k5, LocalDate.of(2026, 11, 1), "Fasilitas", "Engsel lemari pakaian patah dan pintu lemari tidak bisa menutup rapat.", "Selesai", LocalDate.of(2026, 11, 2));
        Keluhan kl8 = createKeluhan("KLH008", k3, LocalDate.of(2026, 10, 30), "Lainnya", "Sinyal Wi-Fi di kamar sangat lemah, mohon dicek ulang router lantai tiga.", "Selesai", LocalDate.of(2026, 10, 31));

        keluhanRepo.saveAll(List.of(kl1, kl2, kl3, kl4, kl5, kl6, kl7, kl8));
    }

    private Kamar createKamar(String id, String fas, String lok, int harga, String status, int luas, int jml) {
        Kamar k = new Kamar();
        k.setIdKamar(id);
        k.setFasilitas(fas);
        k.setLokasi(lok);
        k.setHargaBulanan(harga);
        k.setStatusKamar(status);
        k.setLuasSewaan(luas);
        k.setJumlahKamar(jml);
        return k;
    }

    private Penghuni createPenghuni(String id, String nama, String telp, String kerja, LocalDate daftar, String jk) {
        Penghuni p = new Penghuni();
        p.setIdPenghuni(id);
        p.setNama(nama);
        p.setNoTelepon(telp);
        p.setPekerjaan(kerja);
        p.setTanggalDaftar(daftar);
        p.setJenisKelamin(jk);
        return p;
    }

    private Kontrak createKontrak(String id, Kamar k, Penghuni p, LocalDate mulai, LocalDate selesai, int harga, LocalDate jt, String status) {
        Kontrak c = new Kontrak();
        c.setIdKontrak(id);
        c.setKamar(k);
        c.setPenghuni(p);
        c.setTanggalMulai(mulai);
        c.setTanggalSelesai(selesai);
        c.setHargaSewa(harga);
        c.setTanggalJatuhTempo(jt);
        c.setStatusKontrak(status);
        return c;
    }

    private Pembayaran createPembayaran(String id, Kontrak k, LocalDate periode, LocalDate jt, int tagihan, int dibayar, LocalDate bayar, String status) {
        Pembayaran pb = new Pembayaran();
        pb.setIdPembayaran(id);
        pb.setKontrak(k);
        pb.setPeriodeBulan(periode);
        pb.setTanggalJatuhTempo(jt);
        pb.setJumlahTagihan(tagihan);
        pb.setJumlahDibayar(dibayar);
        pb.setTanggalBayar(bayar);
        pb.setStatusPembayaran(status);
        return pb;
    }

    private Keluhan createKeluhan(String id, Kontrak k, LocalDate tgl, String kat, String desk, String status, LocalDate selesai) {
        Keluhan kl = new Keluhan();
        kl.setIdKeluhan(id);
        kl.setKontrak(k);
        kl.setTanggalKeluhan(tgl);
        kl.setKategori(kat);
        kl.setDeskripsi(desk);
        kl.setStatusPenanganan(status);
        kl.setTanggalSelesai(selesai);
        return kl;
    }
}
