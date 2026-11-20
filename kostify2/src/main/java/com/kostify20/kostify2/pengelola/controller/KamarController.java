package com.kostify20.kostify2.pengelola.controller;

import com.kostify20.kostify2.model.Kamar;
import com.kostify20.kostify2.repository.KamarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pengelola/kamar")
public class KamarController {

    private final KamarRepository kamarRepo;

    public KamarController(KamarRepository kamarRepo) {
        this.kamarRepo = kamarRepo;
    }

    @GetMapping
    public String listKamar(@RequestParam(required = false, defaultValue = "Semua") String status,
                            @RequestParam(required = false) String q,
                            Model model) {
        List<Kamar> semua = kamarRepo.findAll();
        List<Kamar> filtered = semua.stream()
                .filter(k -> "Semua".equalsIgnoreCase(status) || status.equalsIgnoreCase(k.getStatusKamar()))
                .filter(k -> q == null || q.isBlank()
                        || k.getIdKamar().toLowerCase().contains(q.toLowerCase())
                        || (k.getFasilitas() != null && k.getFasilitas().toLowerCase().contains(q.toLowerCase()))
                        || (k.getLokasi() != null && k.getLokasi().toLowerCase().contains(q.toLowerCase())))
                .collect(Collectors.toList());

        model.addAttribute("activeMenu", "kamar");
        model.addAttribute("pageTitle", "Manajemen Kamar");
        model.addAttribute("daftarKamar", filtered);
        model.addAttribute("statusFilter", status);
        model.addAttribute("keyword", q);
        model.addAttribute("countSemua", semua.size());
        model.addAttribute("countTersedia", kamarRepo.countByStatusKamar("Tersedia"));
        model.addAttribute("countTerisi", kamarRepo.countByStatusKamar("Terisi"));
        model.addAttribute("countPerbaikan", kamarRepo.countByStatusKamar("Perbaikan"));
        return "pengelola/kamar";
    }

    @PostMapping("/simpan")
    public String simpanKamar(@RequestParam String idKamar,
                              @RequestParam String lokasi,
                              @RequestParam String fasilitas,
                              @RequestParam Integer hargaBulanan,
                              @RequestParam Integer luasSewaan,
                              @RequestParam String statusKamar) {
        Kamar k = kamarRepo.findById(idKamar.toUpperCase()).orElse(new Kamar());
        k.setIdKamar(idKamar.toUpperCase().trim());
        k.setLokasi(lokasi);
        k.setFasilitas(fasilitas);
        k.setHargaBulanan(hargaBulanan);
        k.setLuasSewaan(luasSewaan);
        k.setJumlahKamar(1);
        k.setStatusKamar(statusKamar);
        kamarRepo.save(k);
        return "redirect:/pengelola/kamar";
    }

    @PostMapping("/hapus/{id}")
    public String hapusKamar(@PathVariable String id) {
        kamarRepo.deleteById(id);
        return "redirect:/pengelola/kamar";
    }
}