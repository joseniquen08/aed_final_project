package pe.edu.utp.final_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

  @GetMapping("/")
  public String home(Model model, HttpServletRequest request) {
    model.addAttribute("module", "index");
    return getUsernameFromCookies(request, model, "index");
  }

  @GetMapping("/buscar")
  public String buscar(Model model, HttpServletRequest request) {
    model.addAttribute("module", "buscar");
    model.addAttribute("results", null);
    return getUsernameFromCookies(request, model, "buscar");
  }

  @GetMapping("/estadisticas")
  public String estadisticas(Model model, HttpServletRequest request) {
    model.addAttribute("module", "estadisticas");
    return getUsernameFromCookies(request, model, "estadisticas");
  }

  public String getUsernameFromCookies(HttpServletRequest request, Model model, String successView) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("setUser")) {
          model.addAttribute("usernameCk", cookie.getValue());
          return successView;
        }
      }
    }
    return "redirect:/login";
  }
}
