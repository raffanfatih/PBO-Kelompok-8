class Staff implements KelolaFasilitas {
    public void cekJadwalRutin() {
        System.out.println("Staf mengecek jadwal pemeliharaan AC bulanan.");
    }

    @Override
    public int hitungBiayaPerbaikan(int hargaSukuCadang, int ongkosTukang) {
        return hargaSukuCadang + ongkosTukang + 50000;
    }

    @Override
    public void cekKondisiFasilitas(int tingkatKerusakanPersen) {
        if (tingkatKerusakanPersen == 100) {
            System.out.println("Laporan Staf: Barang hancur total, tidak bisa diperbaiki.");
        } else if (tingkatKerusakanPersen > 0) {
            System.out.println("Laporan Staf: Barang sedang masuk jadwal perbaikan.");
        } else {
            System.out.println("Laporan Staf: Barang dalam kondisi normal/baik.");
        }
    }
}