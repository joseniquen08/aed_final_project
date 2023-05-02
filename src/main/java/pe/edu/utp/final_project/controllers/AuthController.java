package pe.edu.utp.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pe.edu.utp.final_project.domain.auth.Login;
import pe.edu.utp.final_project.domain.auth.Register;
import pe.edu.utp.final_project.services.IAuthService;

@Controller
public class AuthController {

  @Autowired
  private IAuthService authService;

  @GetMapping("/login")
  public String login(Model model) {
    Login login = new Login();
    model.addAttribute("loginObject", login);
    return "login";
  }

  @PostMapping("/loginAction")
  public String loginAction(@ModelAttribute("loginObject") Login login, Model model) {
    if (authService.authLogin(login)) {
      return "redirect:/";
    }
    return "redirect:/login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    Register register = new Register();
    model.addAttribute("registerObject", register);
    return "register";
  }

  @PostMapping("/registerAction")
  public String registerAction(@ModelAttribute("registerObject") Register register, Model model) {
    if (authService.authRegister(register)) {
      return "redirect:/login";
    }
    return "redirect:/register";
  }
}
