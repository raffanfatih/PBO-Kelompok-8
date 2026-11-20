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
@RequestMapping("/pengelola/kontrak")
public class KontrakController {

    private final KontrakRepository kontrakRepo;
    private final PenghuniRepository penghuniRepo;
    private final KamarRepository kamarRepo;

    public KontrakController(KontrakRepository kontrakRepo,
                             PenghuniRepository penghuniRepo,
                             KamarRepository kamarRepo) {
        this.kontrakRepo = kontrakRepo;
        this.penghuniRepo = penghuniRepo;
        this.kamarRepo = kamarRepo;
    }

    @GetMapping
    public String listKontrak(@RequestParam(required = false, defaultValue = "Semua") String status,
                              @RequestParam(required = false) String q,
                              Model model) {
        List<Kontrak> semua = kontrakRepo.findAll();
        List<Kontrak> filtered = semua.stream()
                .filter(k -> "Semua".equalsIgnoreCase(status) || status.equalsIgnoreCase(k.getStatusKontrak()))
                .filter(k -> q == null || q.isBlank()
                        || (k.getPenghuni() != null && k.getPenghuni().getNama().toLowerCase().contains(q.toLowerCase()))
                        || (k.getKamar() != null && k.getKamar().getIdKamar().toLowerCase().contains(q.toLowerCase())))
                .collect(Collectors.toList());

        model.addAttribute("activeMenu", "kontrak");
        model.addAttribute("pageTitle", "Manajemen Kontrak");
        model.addAttribute("daftarKontrak", filtered);
        model.addAttribute("daftarPenghuni", penghuniRepo.findAll());
        model.addAttribute("daftarKamar", kamarRepo.findAll());
        model.addAttribute("statusFilter", status);
        model.addAttribute("keyword", q);
        model.addAttribute("countSemua", semua.size());
        model.addAttribute("countAktif", kontrakRepo.countByStatusKontrak("Aktif"));
        model.addAttribute("countAkanBerakhir", kontrakRepo.countByStatusKontrak("Akan Berakhir"));
        model.addAttribute("countBerakhir", kontrakRepo.countByStatusKontrak("Berakhir"));
        return "pengelola/kontrak";
    }

    @PostMapping("/simpan")
    public String simpanKontrak(@RequestParam String idPenghuni,
                                @RequestParam String idKamar,
                                @RequestParam LocalDate tanggalMulai,
                                @RequestParam int durasiBulan,
                                @RequestParam Integer hargaSewa) {
        Penghuni p = penghuniRepo.findById(idPenghuni).orElseThrow();
        Kamar km = kamarRepo.findById(idKamar).orElseThrow();
        km.setStatusKamar("Terisi");
        kamarRepo.save(km);

        Kontrak k = new Kontrak();
        k.setIdKontrak("KTR" + String.format("%03d", kontrakRepo.count() + 1));
        k.setPenghuni(p);
        k.setKamar(km);
        k.setTanggalMulai(tanggalMulai);
        k.setTanggalSelesai(tanggalMulai.plusMonths(durasiBulan));
        k.setHargaSewa(hargaSewa);
        k.setTanggalJatuhTempo(tanggalMulai.withDayOfMonth(5));
        k.setStatusKontrak("Aktif");
        kontrakRepo.save(k);

        return "redirect:/pengelola/kontrak";
    }

    @PostMapping("/perpanjang/{id}")
    public String perpanjangKontrak(@PathVariable String id, @RequestParam int durasiBulan) {
        Kontrak k = kontrakRepo.findById(id).orElseThrow();
        LocalDate baseDate = k.getTanggalSelesai().isBefore(LocalDate.now()) ? LocalDate.now() : k.getTanggalSelesai();
        k.setTanggalSelesai(baseDate.plusMonths(durasiBulan));
        k.setStatusKontrak("Aktif");
        kontrakRepo.save(k);
        return "redirect:/pengelola/kontrak";
    }

    @PostMapping("/hapus/{id}")
    public String hapusKontrak(@PathVariable String id) {
        kontrakRepo.deleteById(id);
        return "redirect:/pengelola/kontrak";
    }
}
