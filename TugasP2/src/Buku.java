public class Buku {
    private int idBuku;
    private String judul;
    private String pengarang;
    private int tahunTerbit;
    private GenreBuku genre;

    //constructor untuk menginisialisasi nilai awal
    public Buku(int idBuku, String judul, String pengarang, int tahunTerbit, GenreBuku genre) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.genre = genre;
    }

    //mengambil nilai data
    public int getIdBuku() { return idBuku; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public int getTahunTerbit() { return tahunTerbit; }
    public GenreBuku getGenre() { return genre; }

    //menampilkan identitas
    public void tampilkanInfo() {
        System.out.println("ID Buku   : " + idBuku);
        System.out.println("Judul     : " + judul);
        System.out.println("Pengarang : " + pengarang);
        System.out.println("Genre     : " + genre.getNamaGenre());
    }
}