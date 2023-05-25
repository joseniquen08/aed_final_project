package pe.edu.utp.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.final_project.domain.auth.Login;
import pe.edu.utp.final_project.domain.auth.Register;
import pe.edu.utp.final_project.services.AuthServiceImpl;

@Controller
public class AuthController {

  @Autowired
  private AuthServiceImpl authServiceImpl;

  @GetMapping("/login")
  public String login(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
    Login login = new Login();
    if (setUser.isEmpty()) {
      model.addAttribute("loginObject", login);
      return "login";
    }
    return "redirect:/";
  }

  @PostMapping("/loginAction")
  public String loginAction(@ModelAttribute("loginObject") Login login, Model model,
      @CookieValue(value = "setUser", defaultValue = "") String setUser, HttpServletRequest request,
      HttpServletResponse response) {
    if (authServiceImpl.authLogin(login)) {
      setUser = login.getUsername();
      Cookie cookie = new Cookie("setUser", setUser);
      response.addCookie(cookie);
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
    if (authServiceImpl.authRegister(register)) {
      return "redirect:/login";
    }
    return "redirect:/register";
  }

  @GetMapping("/logout")
  public String logout(HttpServletResponse response) {
    Cookie deleteCookie = new Cookie("setUser", null);
    deleteCookie.setMaxAge(0);
    response.addCookie(deleteCookie);
    return "redirect:/login";
  }
}
