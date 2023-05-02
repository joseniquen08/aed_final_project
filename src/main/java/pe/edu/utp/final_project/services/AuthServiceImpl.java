package pe.edu.utp.final_project.services;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import pe.edu.utp.final_project.domain.auth.Login;
import pe.edu.utp.final_project.domain.auth.Register;

@Service
public class AuthServiceImpl implements IAuthService {

  @Override
  public boolean authLogin(Login login) {
    try {
      String filePath = "./src/main/resources/files/Usuarios.csv";
      FileReader fileReader = new FileReader(filePath);

      try (CSVReader openCSVReader = new CSVReader(fileReader)) {
        String[] record;

        while ((record = openCSVReader.readNext()) != null) {
          if (record[1].equals(login.getUsername()) && record[2].equals(login.getPassword())) {
            return true;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CsvValidationException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean authRegister(Register register) {
    try {
      String filePath = "./src/main/resources/files/Usuarios.csv";
      FileWriter fileWriter = new FileWriter(filePath, true);

      CSVWriter openCsvWriter = new CSVWriter(fileWriter);
      String[] record = { register.getFullname(), register.getUsername(), register.getPassword() };
      openCsvWriter.writeNext(record);
      openCsvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

}
