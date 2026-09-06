public class Main {
    public static void main(String[] args) {
        System.out.println("SISTEM MANAJEMEN PERPUSTAKAAN");

        // 1. Instansiasi Objek GenreBuku
        GenreBuku[] daftarGenre = {
            new GenreBuku(1, "Fiksi", "Buku cerita imajinatif"),
            new GenreBuku(2, "Teknologi", "Buku ilmu komputer dan pemrograman")
        };

        // 2. Instansiasi Objek Buku dengan relasi Genre
        Buku[] daftarBuku = {
            new Buku(101, "Harry Potter", "J.K. Rowling", 1998, 2, daftarGenre[0]),
            new Buku(102, "Clean Code", "Robert C. Martin", 2008, 1, daftarGenre[1]),
            new Buku(103, "Design Patterns", "Gang of Four", 1994, 3, daftarGenre[1])
        };

        // 3. Instansiasi Objek Member
        Member member1 = new Member(1001, "Azhar", "azhar78@gmail.com", "081234567890", "2026-09-02");
        Member member2 = new Member(1002, "Raihan", "mraihaner@gmail.com", "080987654321", "2026-09-03");

        // 4. Proses Transaksi (Simulasi)
        System.out.println(">>> SIMULASI PEMINJAMAN");
        member1.pinjamBuku(daftarBuku[0]);
        member1.pinjamBuku(daftarBuku[1]);
        
        member2.pinjamBuku(daftarBuku[1]);
        member2.pinjamBuku(daftarBuku[2]);

        // 5. Lihat Data Pinjaman
        member1.lihatBukuPinjaman();
        member2.lihatBukuPinjaman();

        // 6. Proses Pengembalian
        System.out.println("\n>>> SIMULASI PENGEMBALIAN");
        member1.kembaliBuku(daftarBuku[1]); 

        // 7. Lihat Status Stok Buku Terakhir
        System.out.println("\n>>> STATUS STOK BUKU AKHIR");
        for (Buku b : daftarBuku) {
            System.out.println(b);
        }
    }
}