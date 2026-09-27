abstract class Pengguna {
    public void login() {
        System.out.println("Pengguna berhasil masuk ke sistem Kostify.");
    }

    public abstract double totalTagihan(int hargaPerBulan, int lamaSewa);
    public abstract void cekStatusMember(int lamaMenyewaBulan);
}