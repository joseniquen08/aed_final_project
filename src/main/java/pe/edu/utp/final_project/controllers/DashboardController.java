package pe.edu.utp.final_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("module", "index");
    return "index";
  }

  @GetMapping("/buscar")
  public String buscar(Model model) {
    model.addAttribute("module", "buscar");
    model.addAttribute("results", null);
    return "buscar";
  }
}
