import converter.ConverterRegistration;
import model.Registration;
import repository.RegistrationRepository;
import util.DatabaseConnection;

import java.util.List;

public class BacenIntegrationApplication {
    public static void main(String[] args) {

        DatabaseConnection.CreateConnection();
        ConverterRegistration converterRegistration = new ConverterRegistration();
        List<Registration> registrationList = converterRegistration.converter();
        RegistrationRepository repository = new RegistrationRepository();

        for (Registration registration : registrationList) {
            repository.save(registration);
        }
    }
}
