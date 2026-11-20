package com.kostify20.kostify2.penghuni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/penghuni")
public class PenghuniPortalController {

    // Endpoint placeholder untuk pengembangan peran Penghuni berikutnya
    // (Login Penghuni, Tagihan Saya, Ajukan Keluhan, Kontrak Saya)
    @GetMapping("/login")
    public String portalPenghuniPlaceholder() {
        return "penghuni/portal-placeholder";
    }
}
