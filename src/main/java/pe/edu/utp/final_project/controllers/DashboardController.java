package pe.edu.utp.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import pe.edu.utp.final_project.services.DashboardServiceImpl;

@Controller
public class DashboardController {

  @Autowired
  private DashboardServiceImpl dashboardServiceImpl;

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

  @PostMapping("/buscar")
  public String buscarAction(@RequestParam("query") String query, @RequestParam("type") String type,
      HttpServletRequest request, Model model) {
    model.addAttribute("module", "buscar");
    dashboardServiceImpl.searchFisrt(query, type);
    model.addAttribute("results", query);
    return getUsernameFromCookies(request, model, "buscar");
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
