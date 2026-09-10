//class EBook mewarisi atribut dan metode dari class Buku
public class EBook extends Buku {
    private String linkDownload;

    //constructor untuk mengisi data spesifik beserta data dasarnya
    public EBook(int idBuku, String judul, String pengarang, int tahunTerbit, GenreBuku genre, String linkDownload) {
        // memanggil constructor dari class Buku untuk mengisi data dasar
        super(idBuku, judul, pengarang, tahunTerbit, genre);
        this.linkDownload = linkDownload;
    }

    public String getLinkDownload() { return linkDownload; }

    //fungsi khusus untuk simulasi meminjam EBook
    public boolean pinjam() {
        return true; 
    }

    //menampilkan info spesifik dengan memanggil data dari Buku.java menggunakan getter
    public void tampilkanInfoDigital() {
        System.out.print("[E-Book] " + getJudul() + " | Link: " + linkDownload);
    }
}