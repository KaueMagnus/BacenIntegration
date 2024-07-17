package converter;

import model.Registration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConverterRegistration {
    public List<Registration> converter() {
        Path bacenFile = Paths.get("/Users/kauemagnus/Documents/BacenIntegration/bacen-cadastros.csv");

        List<Registration> registrationObjects = new ArrayList<>();

        try {

            // DOMINAR AS PRINCIPAIS CLASSES DO JAVA:
            // Integer, Double, BigDecimal, FIle, Files, Path, Paths
            // Math, String, StringBuilder

            List<String> registers = Files.readAllLines(bacenFile);
            System.out.println("Obtained rows: " + registers.size());


            // Métodos: nome + parametros + tipo de retorno
            for (String register : registers) {
                String[] fields = register.split(";");


                if (fields.length == 5) {
                    Registration registrationObject = new Registration();

                    try {
                        registrationObject.code = Integer.parseInt(fields[0]);
                        registrationObject.name = fields[1];
                        registrationObject.gender = fields[2];
                        registrationObject.minIncome = Double.parseDouble(fields[3]);
                        registrationObject.restriction = fields[4].equals("T") ? true : false;

                        registrationObjects.add(registrationObject);
                    } catch (NumberFormatException e) {
                        System.err.println("Error converting data for registration: " + register);
                    }
                } else {
                    System.err.println("Invalid format for registration: " + register);
                }

            }
            System.out.println("Finishing the conversion process.");

        } catch (IOException e) {
            System.err.println("An error occurred! " + e.getMessage());
        }

        return registrationObjects;
    }
}