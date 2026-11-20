package com.kostify20.kostify2.pengelola.controller;

import com.kostify20.kostify2.model.Kamar;
import com.kostify20.kostify2.model.Kontrak;
import com.kostify20.kostify2.model.Penghuni;
import com.kostify20.kostify2.repository.KamarRepository;
import com.kostify20.kostify2.repository.KontrakRepository;
import com.kostify20.kostify2.repository.PenghuniRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pengelola/penghuni")
public class PenghuniController {

    private final PenghuniRepository penghuniRepo;
    private final KamarRepository kamarRepo;
    private final KontrakRepository kontrakRepo;

    public PenghuniController(PenghuniRepository penghuniRepo,
                              KamarRepository kamarRepo,
                              KontrakRepository kontrakRepo) {
        this.penghuniRepo = penghuniRepo;
        this.kamarRepo = kamarRepo;
        this.kontrakRepo = kontrakRepo;
    }

    @GetMapping
    public String listPenghuni(@RequestParam(required = false) String q, Model model) {
        List<Penghuni> semua = penghuniRepo.findAll();
        List<Penghuni> filtered = semua.stream()
                .filter(p -> q == null || q.isBlank()
                        || p.getNama().toLowerCase().contains(q.toLowerCase())
                        || (p.getNoTelepon() != null && p.getNoTelepon().contains(q))
                        || (p.getPekerjaan() != null && p.getPekerjaan().toLowerCase().contains(q.toLowerCase())))
                .collect(Collectors.toList());

        model.addAttribute("activeMenu", "penghuni");
        model.addAttribute("pageTitle", "Manajemen Penghuni");
        model.addAttribute("daftarPenghuni", filtered);
        model.addAttribute("kamarKosong", kamarRepo.findByStatusKamar("Tersedia"));
        model.addAttribute("daftarKontrak", kontrakRepo.findAll());
        model.addAttribute("keyword", q);
        model.addAttribute("totalPenghuni", semua.size());
        return "pengelola/penghuni";
    }

    @PostMapping("/simpan")
    public String simpanPenghuni(@RequestParam(required = false) String idPenghuni,
                                 @RequestParam String nama,
                                 @RequestParam String noTelepon,
                                 @RequestParam String pekerjaan,
                                 @RequestParam String jenisKelamin,
                                 @RequestParam LocalDate tanggalDaftar,
                                 @RequestParam(required = false) String idKamar) {
        Penghuni p;
        if (idPenghuni != null && !idPenghuni.isBlank()) {
            p = penghuniRepo.findById(idPenghuni).orElse(new Penghuni());
        } else {
            p = new Penghuni();
            p.setIdPenghuni("PGH" + String.format("%03d", penghuniRepo.count() + 1));
        }

        p.setNama(nama);
        p.setNoTelepon(noTelepon);
        p.setPekerjaan(pekerjaan);
        p.setJenisKelamin(jenisKelamin);
        p.setTanggalDaftar(tanggalDaftar);
        penghuniRepo.save(p);

        // Jika memilih kamar kosong saat tambah penghuni, otomatis buat kontrak awal 12 bulan
        if (idKamar != null && !idKamar.isBlank()) {
            Kamar kamar = kamarRepo.findById(idKamar).orElse(null);
            if (kamar != null) {
                kamar.setStatusKamar("Terisi");
                kamarRepo.save(kamar);

                Kontrak k = new Kontrak();
                k.setIdKontrak("KTR" + String.format("%03d", kontrakRepo.count() + 1));
                k.setKamar(kamar);
                k.setPenghuni(p);
                k.setTanggalMulai(tanggalDaftar);
                k.setTanggalSelesai(tanggalDaftar.plusMonths(12));
                k.setHargaSewa(kamar.getHargaBulanan());
                k.setTanggalJatuhTempo(tanggalDaftar.withDayOfMonth(5));
                k.setStatusKontrak("Aktif");
                kontrakRepo.save(k);
            }
        }
        return "redirect:/pengelola/penghuni";
    }

    @PostMapping("/hapus/{id}")
    public String hapusPenghuni(@PathVariable String id) {
        penghuniRepo.deleteById(id);
        return "redirect:/pengelola/penghuni";
    }
}
