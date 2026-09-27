public class Main {
    public static void main(String[] args) {
        System.out.println("\n=== KOSTIFY ===");

        System.out.println("\n--- Sesi Pemilik ---");
        Pemilik pakBudi = new Pemilik();
        pakBudi.login();
        pakBudi.kategoriKamar(1500000); 
        
        double pendapatanBersih = pakBudi.totalTagihan(1500000, 3);
        System.out.println("Perkiraan Pendapatan Bersih (3 bulan): Rp " + pendapatanBersih);
        
        pakBudi.cekStatusMember(15);
        
        System.out.println("\n--- Sesi Penghuni ---");
        Penghuni andi = new Penghuni();
        andi.login();
        
        double bayarKos = andi.totalTagihan(1500000, 3);
        System.out.println("Total yang harus dibayar Andi: Rp " + bayarKos);
        
        double patungan = andi.hitungPatunganListrik(500000, 2); 
        System.out.println("Patungan listrik Andi bulan ini: Rp " + patungan);
        
        System.out.println("\n--- Sesi Staff ---");
        Staff tukang = new Staff();
        tukang.cekJadwalRutin();
        tukang.cekKondisiFasilitas(80); 
        
        int notaPerbaikan = tukang.hitungBiayaPerbaikan(100000, 150000); 
        System.out.println("Total Nota Perbaikan AC: Rp " + notaPerbaikan);

        System.out.println("\n--- Sesi Admin ---");
        Admin admin = new Admin();
        admin.cetakLaporan();
        admin.evaluasiKontrak(1); 
        
        double dendaAndi = admin.hitungDendaKeterlambatan(10, 20000); 
        System.out.println("Total denda keterlambatan Andi: Rp " + dendaAndi);
    }
}