package com.kostify20.kostify2.pengelola.controller;

import com.kostify20.kostify2.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pengelola")
public class DashboardController {

    private final KamarRepository kamarRepo;
    private final PenghuniRepository penghuniRepo;
    private final KontrakRepository kontrakRepo;
    private final PembayaranRepository pembayaranRepo;
    private final KeluhanRepository keluhanRepo;

    public DashboardController(KamarRepository kamarRepo,
                               PenghuniRepository penghuniRepo,
                               KontrakRepository kontrakRepo,
                               PembayaranRepository pembayaranRepo,
                               KeluhanRepository keluhanRepo) {
        this.kamarRepo = kamarRepo;
        this.penghuniRepo = penghuniRepo;
        this.kontrakRepo = kontrakRepo;
        this.pembayaranRepo = pembayaranRepo;
        this.keluhanRepo = keluhanRepo;
    }

    @GetMapping("/beranda")
    public String beranda(Model model) {
        long totalKamar = kamarRepo.count();
        long kamarTerisi = kamarRepo.countByStatusKamar("Terisi");
        long kamarTersedia = kamarRepo.countByStatusKamar("Tersedia");
        long totalPenghuni = penghuniRepo.count();
        long kontrakAkanBerakhir = kontrakRepo.countByStatusKontrak("Akan Berakhir");
        long keluhanMenunggu = keluhanRepo.countByStatusPenanganan("Menunggu");

        model.addAttribute("activeMenu", "beranda");
        model.addAttribute("pageTitle", "Beranda");
        model.addAttribute("totalKamar", totalKamar);
        model.addAttribute("kamarTerisi", kamarTerisi);
        model.addAttribute("kamarTersedia", kamarTersedia);
        model.addAttribute("totalPenghuni", totalPenghuni);
        model.addAttribute("totalPendapatan", pembayaranRepo.totalPembayaranDiterima());
        model.addAttribute("totalTerlambat", pembayaranRepo.totalTerlambat());
        model.addAttribute("kontrakAkanBerakhir", kontrakAkanBerakhir);
        model.addAttribute("keluhanMenunggu", keluhanMenunggu);
        model.addAttribute("daftarKontrak", kontrakRepo.findAll());
        model.addAttribute("daftarKeluhan", keluhanRepo.findAll());
        return "pengelola/beranda";
    }
}