package com.kostify20.kostify2.pengelola.controller;

import com.kostify20.kostify2.model.Keluhan;
import com.kostify20.kostify2.repository.KeluhanRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pengelola/keluhan")
public class KeluhanController {

    private final KeluhanRepository keluhanRepo;

    public KeluhanController(KeluhanRepository keluhanRepo) {
        this.keluhanRepo = keluhanRepo;
    }

    @GetMapping
    public String listKeluhan(@RequestParam(required = false, defaultValue = "Semua") String status,
                              @RequestParam(required = false, defaultValue = "Semua") String kategori,
                              @RequestParam(required = false) String q,
                              Model model) {
        List<Keluhan> semua = keluhanRepo.findAll();
        List<Keluhan> filtered = semua.stream()
                .filter(kl -> "Semua".equalsIgnoreCase(status) || status.equalsIgnoreCase(kl.getStatusPenanganan()))
                .filter(kl -> "Semua".equalsIgnoreCase(kategori) || kategori.equalsIgnoreCase(kl.getKategori()))
                .filter(kl -> q == null || q.isBlank()
                        || kl.getDeskripsi().toLowerCase().contains(q.toLowerCase())
                        || (kl.getKontrak() != null && kl.getKontrak().getPenghuni().getNama().toLowerCase().contains(q.toLowerCase()))
                        || (kl.getKontrak() != null && kl.getKontrak().getKamar().getIdKamar().toLowerCase().contains(q.toLowerCase())))
                .collect(Collectors.toList());

        model.addAttribute("activeMenu", "keluhan");
        model.addAttribute("pageTitle", "Manajemen Keluhan");
        model.addAttribute("daftarKeluhan", filtered);
        model.addAttribute("statusFilter", status);
        model.addAttribute("kategoriFilter", kategori);
        model.addAttribute("keyword", q);
        model.addAttribute("countSemua", semua.size());
        model.addAttribute("countMenunggu", keluhanRepo.countByStatusPenanganan("Menunggu"));
        model.addAttribute("countDiproses", keluhanRepo.countByStatusPenanganan("Diproses"));
        model.addAttribute("countSelesai", keluhanRepo.countByStatusPenanganan("Selesai"));
        return "pengelola/keluhan";
    }

    @PostMapping("/ubah-status/{id}")
    public String ubahStatus(@PathVariable String id, @RequestParam String statusBaru) {
        Keluhan kl = keluhanRepo.findById(id).orElseThrow();
        kl.setStatusPenanganan(statusBaru);
        if ("Selesai".equalsIgnoreCase(statusBaru)) {
            kl.setTanggalSelesai(LocalDate.now());
        }
        keluhanRepo.save(kl);
        return "redirect:/pengelola/keluhan";
    }
}
