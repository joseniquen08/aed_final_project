package pe.edu.utp.final_project.services;

import pe.edu.utp.final_project.domain.auth.Login;
import pe.edu.utp.final_project.domain.auth.Register;

public interface IAuthService {

  boolean authLogin(Login login);

  boolean authRegister(Register register);
}
