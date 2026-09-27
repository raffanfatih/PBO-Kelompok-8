class Admin implements KelolaKontrak {
    public void cetakLaporan() {
        System.out.println("Admin sedang mencetak dokumen laporan penyewaan.");
    }

    @Override
    public double hitungDendaKeterlambatan(int hariTelat, double dendaPerHari) {
        if (hariTelat > 7) {
            return (hariTelat * dendaPerHari) * 2;
        }
        return hariTelat * dendaPerHari;
    }

    @Override
    public void evaluasiKontrak(int sisaBulan) {
        if (sisaBulan <= 1) {
            System.out.println("Tugas Admin: Membuat surat peringatan perpanjangan kontrak H-30 hari.");
        } else {
            System.out.println("Tugas Admin: Mengarsip dokumen kontrak dengan aman.");
        }
    }
}