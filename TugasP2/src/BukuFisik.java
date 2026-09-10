//class BukuFisik mewarisi atribut dan metode dari class Buku
public class BukuFisik extends Buku {
    private int stok;

    //constructor untuk mengisi data spesifik beserta data dasarnya
    public BukuFisik(int idBuku, String judul, String pengarang, int tahunTerbit, GenreBuku genre, int stok) {
        // memanggil constructor dari class Buku untuk mengisi data dasar
        super(idBuku, judul, pengarang, tahunTerbit, genre);
        this.stok = stok;
    }

    public int getStok() { return stok; }

    //fungsi untuk simulasi meminjam buku fisik
    public boolean pinjam() {
        if (stok > 0) {
            stok--;
            return true;
        }
        return false;
    }

    //fungsi untuk mengembalikan buku fisik
    public void kembali() {
        stok++;
    }

    //menampilkan info spesifik dengan memanggil data dari Buku.java menggunakan getter
    public void tampilkanInfoFisik() {
        System.out.print("[Buku Fisik] " + getJudul() + " | Sisa Stok: " + stok);
    }
}