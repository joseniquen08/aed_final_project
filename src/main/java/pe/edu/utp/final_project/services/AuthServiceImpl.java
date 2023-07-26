package pe.edu.utp.final_project.services;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import at.favre.lib.crypto.bcrypt.BCrypt;
import pe.edu.utp.final_project.domain.auth.Login;
import pe.edu.utp.final_project.domain.auth.Register;

@Service
public class AuthServiceImpl implements IAuthService {

  @Override
  public boolean authLogin(Login login) {
    try {
      String filePath = "./src/main/resources/files/Usuarios.csv";
      FileReader fileReader = new FileReader(filePath, Charset.forName("UTF-8"));

      try (CSVReader openCSVReader = new CSVReader(fileReader)) {
        String[] record;

        while ((record = openCSVReader.readNext()) != null) {
          BCrypt.Result result = BCrypt.verifyer().verify(login.getPassword().toCharArray(), record[2]);
          if (record[1].equals(login.getUsername()) && result.verified) {
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
      FileWriter fileWriter = new FileWriter(filePath, Charset.forName("UTF-8"), true);

      CSVWriter openCsvWriter = new CSVWriter(fileWriter);
      String[] record = { register.getFullname(), register.getUsername(),
          BCrypt.withDefaults().hashToString(12, register.getPassword().toCharArray()) };
      openCsvWriter.writeNext(record);
      openCsvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

}
