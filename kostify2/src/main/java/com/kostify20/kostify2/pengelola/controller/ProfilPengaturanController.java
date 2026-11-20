package com.kostify20.kostify2.pengelola.controller;

import com.kostify20.kostify2.config.DataSeeder;
import com.kostify20.kostify2.repository.KamarRepository;
import com.kostify20.kostify2.repository.KontrakRepository;
import com.kostify20.kostify2.repository.PenghuniRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pengelola")
public class ProfilPengaturanController {

    private final KamarRepository kamarRepo;
    private final PenghuniRepository penghuniRepo;
    private final KontrakRepository kontrakRepo;
    private final DataSeeder dataSeeder;

    public ProfilPengaturanController(KamarRepository kamarRepo,
                                      PenghuniRepository penghuniRepo,
                                      KontrakRepository kontrakRepo,
                                      DataSeeder dataSeeder) {
        this.kamarRepo = kamarRepo;
        this.penghuniRepo = penghuniRepo;
        this.kontrakRepo = kontrakRepo;
        this.dataSeeder = dataSeeder;
    }

    @GetMapping("/profil")
    public String profil(Model model) {
        model.addAttribute("activeMenu", "profil");
        model.addAttribute("pageTitle", "Profil Pengelola");
        model.addAttribute("totalKamar", kamarRepo.count());
        model.addAttribute("totalPenghuni", penghuniRepo.count());
        model.addAttribute("totalKontrak", kontrakRepo.count());
        return "pengelola/profil";
    }

    @PostMapping("/profil/edit")
    public String editProfil(@RequestParam String nama,
                             @RequestParam String email,
                             @RequestParam String telepon,
                             HttpSession session,
                             RedirectAttributes ra) {
        session.setAttribute("adminNama", nama);
        session.setAttribute("adminEmail", email);
        session.setAttribute("adminTelepon", telepon);
        ra.addFlashAttribute("pesanSukses", "Profil pengelola berhasil diperbarui.");
        return "redirect:/pengelola/profil";
    }

    @GetMapping("/pengaturan")
    public String pengaturan(Model model) {
        model.addAttribute("activeMenu", "pengaturan");
        model.addAttribute("pageTitle", "Pengaturan");
        return "pengelola/pengaturan";
    }

    @PostMapping("/pengaturan/kos")
    public String simpanInfoKos(@RequestParam String kosNama,
                                @RequestParam String kosAlamat,
                                @RequestParam String jatuhTempoDefault,
                                HttpSession session,
                                RedirectAttributes ra) {
        session.setAttribute("kosNama", kosNama);
        session.setAttribute("kosAlamat", kosAlamat);
        session.setAttribute("jatuhTempoDefault", jatuhTempoDefault);
        ra.addFlashAttribute("pesanSukses", "Informasi kos berhasil disimpan.");
        return "redirect:/pengelola/pengaturan";
    }

    @PostMapping("/pengaturan/reset-demo")
    public String resetDemo(RedirectAttributes ra) {
        dataSeeder.resetData();
        ra.addFlashAttribute("pesanSukses", "Seluruh data demo telah dikembalikan ke kondisi awal.");
        return "redirect:/pengelola/pengaturan";
    }
}
