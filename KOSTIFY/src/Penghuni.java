class Penghuni extends Pengguna {
    public double hitungPatunganListrik(double totalTagihanListrik, int jumlahAlatElektronik) {
        double biayaDasar = totalTagihanListrik / 10; 
        return biayaDasar + (jumlahAlatElektronik * 25000); 
    }

    @Override
    public double totalTagihan(int hargaPerBulan, int lamaSewa) {
        return hargaPerBulan * lamaSewa;
    }

    @Override
    public void cekStatusMember(int lamaMenyewaBulan) {
        if (lamaMenyewaBulan >= 6) {
            System.out.println("Penghuni sudah melewati masa kontrak minimum 6 bulan.");
        } else {
            System.out.println("Penghuni belum melewati masa kontrak minimum.");
        }
    }
}