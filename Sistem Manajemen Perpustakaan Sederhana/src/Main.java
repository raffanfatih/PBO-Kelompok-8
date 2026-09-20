public class Main {
    public static void main(String[] args) {
        System.out.println("\nSISTEM MANAJEMEN PERPUSTAKAAN");

        // 1. Instansiasi Objek GenreBuku
        GenreBuku genre1 = new GenreBuku(1, "Fiksi", "Buku cerita imajinatif");
        GenreBuku genre2 = new GenreBuku(2, "Teknologi", "Buku ilmu komputer dan pemrograman");
        
        // 2. Instansiasi Objek Buku dengan relasi Genre
        BukuFisik bukuFisik1 = new BukuFisik(101, "Harry Potter", "J.K. Rowling", 1998, genre1, 2);           
        EBook eBook1 = new EBook(103, "Design Patterns", "Gang of Four", 1994, genre2, "ipb.link/kelompok-8");

        // 3. Instansiasi Objek Member
        Member member1 = new Member(1001, "Azhar", "azhar78@gmail.com", "081234567890", "2026-09-02");
        Member member2 = new Member(1002, "Raihan", "mraihaner@gmail.com", "080987654321", "2026-09-03");

        // 4. Proses Transaksi (Simulasi)
        System.out.println("\n--- SIMULASI PEMINJAMAN ---");
        if (bukuFisik1.pinjam()){
            member1.pinjamBuku(bukuFisik1);
        }

        if (eBook1.pinjam()){
            member2.pinjamBuku(eBook1);
        }

        System.out.println("\n--- SIMULASI PENGEMBALIAN ---");
        member1.kembaliBuku(bukuFisik1); 
        bukuFisik1.kembali();

        System.out.println("\n--- STATUS BUKU DI PERPUSTAKAAN ---");
        bukuFisik1.tampilkanInfo(); 
        bukuFisik1.tampilkanInfoFisik(); 
        System.out.println("\n");

        eBook1.tampilkanInfo();
        eBook1.tampilkanInfoDigital(); 
        System.out.println("");
    }
}