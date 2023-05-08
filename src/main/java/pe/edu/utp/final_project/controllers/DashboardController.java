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
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("setUser")) {
          model.addAttribute("usernameCk", cookie.getValue());
          break;
        } else {
          return "redirect:/login";
        }
      }
    } else {
      return "redirect:/login";
    }
    return "index";
  }

  @GetMapping("/buscar")
  public String buscar(Model model) {
    model.addAttribute("module", "buscar");
    model.addAttribute("results", null);
    return "buscar";
  }
}
