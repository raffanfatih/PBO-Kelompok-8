class Pemilik extends Pengguna implements KelolaFasilitas, KelolaKontrak {
    public void kategoriKamar(double hargaSewa) {
        if (hargaSewa >= 1000000) {
            System.out.println("Kategori Kamar: VIP (Fasilitas Lengkap)");
        } else if (hargaSewa >= 500000) {
            System.out.println("Kategori Kamar: Reguler (Fasilitas Standar)");
        } else {
            System.out.println("Kategori Kamar: Ekonomi (Fasilitas Dasar)");
        }
    }

    @Override
    public double totalTagihan(int hargaPerBulan, int lamaSewa) {
        double total = hargaPerBulan * lamaSewa;
        return total - (total * 0.05); 
    }

    @Override
    public void cekStatusMember(int lamaMenyewaBulan) {
        if (lamaMenyewaBulan > 12) {
            System.out.println("Status: Penghuni Lama (Berhak mendapat diskon).");
        } else {
            System.out.println("Status: Penghuni Baru.");
        }
    }

    @Override
    public int hitungBiayaPerbaikan(int hargaSukuCadang, int ongkosTukang) {
        return hargaSukuCadang + ongkosTukang;
    }

    @Override
    public void cekKondisiFasilitas(int tingkatKerusakanPersen) {
        if (tingkatKerusakanPersen > 70) {
            System.out.println("Kondisi: Rusak Berat. Fasilitas harus diganti baru.");
        } else {
            System.out.println("Kondisi: Rusak Ringan. Fasilitas masih bisa diperbaiki.");
        }
    }

    @Override
    public double hitungDendaKeterlambatan(int hariTelat, double dendaPerHari) {
        return hariTelat * dendaPerHari;
    }

    @Override
    public void evaluasiKontrak(int sisaBulan) {
        if (sisaBulan == 0) {
            System.out.println("Kontrak Habis. Segera hubungi penghuni untuk perpanjangan.");
        } else {
            System.out.println("Kontrak masih berjalan selama " + sisaBulan + " bulan lagi.");
        }
    }
}