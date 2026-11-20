package com.kostify20.kostify2.pengelola.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/")
    public String rootRedirect(HttpSession session) {
        if (session.getAttribute("adminLogin") != null) {
            return "redirect:/pengelola/beranda";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "pengelola/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        if ("admin@kostify.id".equalsIgnoreCase(email.trim()) && !password.isBlank()) {
            session.setAttribute("adminLogin", true);
            session.setAttribute("adminNama", "Bagas Hartono");
            session.setAttribute("adminEmail", "admin@kostify.id");
            session.setAttribute("kosNama", "Kos Griya Asri");
            session.setAttribute("kosAlamat", "Jl. Kenanga No. 12, Sleman, Yogyakarta");
            return "redirect:/pengelola/beranda";
        }
        model.addAttribute("error", "Email atau password tidak sesuai. Gunakan admin@kostify.id");
        return "pengelola/login";
    }

    @GetMapping("/keluar")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}