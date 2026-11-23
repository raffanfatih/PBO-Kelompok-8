package com.kostify20.kostify2.pengelola.controller;

import com.kostify20.kostify2.model.Kontrak;
import com.kostify20.kostify2.model.Pembayaran;
import com.kostify20.kostify2.repository.KontrakRepository;
import com.kostify20.kostify2.repository.PembayaranRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pengelola/pembayaran")
public class PembayaranController {

    private final PembayaranRepository pembayaranRepo;
    private final KontrakRepository kontrakRepo;

    public PembayaranController(PembayaranRepository pembayaranRepo, KontrakRepository kontrakRepo) {
        this.pembayaranRepo = pembayaranRepo;
        this.kontrakRepo = kontrakRepo;
    }

    @GetMapping
    public String listPembayaran(@RequestParam(required = false, defaultValue = "Semua") String status,
                                 @RequestParam(required = false) String q,
                                 Model model) {
        List<Pembayaran> semua = pembayaranRepo.findAll();
        List<Pembayaran> filtered = semua.stream()
                .filter(p -> "Semua".equalsIgnoreCase(status) || status.equalsIgnoreCase(p.getStatusPembayaran()))
                .filter(p -> q == null || q.isBlank()
                        || (p.getKontrak() != null && p.getKontrak().getPenghuni().getNama().toLowerCase().contains(q.toLowerCase()))
                        || (p.getKontrak() != null && p.getKontrak().getKamar().getIdKamar().toLowerCase().contains(q.toLowerCase())))
                .collect(Collectors.toList());

        model.addAttribute("activeMenu", "pembayaran");
        model.addAttribute("pageTitle", "Manajemen Pembayaran");
        model.addAttribute("daftarPembayaran", filtered);
        model.addAttribute("daftarKontrak", kontrakRepo.findAll());
        model.addAttribute("statusFilter", status);
        model.addAttribute("keyword", q);
        model.addAttribute("totalDiterima", pembayaranRepo.totalPembayaranDiterima());
        model.addAttribute("totalBelumDibayar", pembayaranRepo.totalBelumDibayar());
        model.addAttribute("totalTerlambat", pembayaranRepo.totalTerlambat());
        model.addAttribute("countSemua", semua.size());
        model.addAttribute("countLunas", pembayaranRepo.countByStatusPembayaran("Lunas"));
        model.addAttribute("countBelumLunas", pembayaranRepo.countByStatusPembayaran("Belum Lunas"));
        model.addAttribute("countTerlambat", pembayaranRepo.countByStatusPembayaran("Terlambat"));
        return "pengelola/pembayaran";
    }

    @PostMapping("/tambah")
    public String tambahTagihan(@RequestParam String idKontrak,
                                @RequestParam LocalDate periodeBulan,
                                @RequestParam LocalDate tanggalJatuhTempo,
                                @RequestParam Integer jumlahTagihan) {
        Kontrak k = kontrakRepo.findById(idKontrak).orElseThrow();
        Pembayaran pb = new Pembayaran();
        pb.setIdPembayaran("BYR" + String.format("%03d", pembayaranRepo.count() + 1));
        pb.setKontrak(k);
        pb.setPeriodeBulan(periodeBulan);
        pb.setTanggalJatuhTempo(tanggalJatuhTempo);
        pb.setJumlahTagihan(jumlahTagihan);
        pb.setJumlahDibayar(0);
        pb.setStatusPembayaran(tanggalJatuhTempo.isBefore(LocalDate.now()) ? "Terlambat" : "Belum Lunas");
        pembayaranRepo.save(pb);
        return "redirect:/pengelola/pembayaran";
    }

    @PostMapping("/catat-bayar/{id}")
    public String catatBayar(@PathVariable String id) {
        Pembayaran pb = pembayaranRepo.findById(id).orElseThrow();
        pb.setJumlahDibayar(pb.getJumlahTagihan());
        pb.setTanggalBayar(LocalDate.now());
        pb.setStatusPembayaran("Lunas");
        pembayaranRepo.save(pb);
        return "redirect:/pengelola/pembayaran";
    }
}
