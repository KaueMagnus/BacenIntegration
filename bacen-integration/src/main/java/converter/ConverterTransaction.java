package converter;

import model.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConverterTransaction {
    private Path transactionsFile;

    public ConverterTransaction() {
        this.transactionsFile = Paths.get("/Users/kauemagnus/Documents/BacenIntegration/bacen-movimentacoes.txt");
    }

    public void convert() {
        try {
            // CONVERTENDO A DATA QUE VEM EM STRING PARA O FORMATO DATA NORMAL (YYYY/MM/DD)
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            List<String> transactions = Files.readAllLines(transactionsFile);
            List<Transaction> transactionObjects = new ArrayList<>();

            for (String transaction : transactions) {
                System.out.println(transaction);
                Transaction transactionObject = new Transaction();

                transactionObject.idRegistration = Integer.parseInt(transaction.substring(0, 6));
                transactionObject.description = transaction.substring(6, 30).trim();
                Integer value = Integer.parseInt(transaction.substring(36, 44));
                transactionObject.value = new BigDecimal(value)
                        .divide(new BigDecimal(100))
                        .doubleValue();

                transactionObject.date = LocalDate.parse(transaction.substring(44, 52), dateFormatter);

                transactionObjects.add(transactionObject);
            }
            System.out.println(transactionObjects.size());
            System.out.println("Conversão das movimentações realizada com sucesso!");
        } catch (IOException exception) {
            System.err.println("An error occurred! " + exception.getMessage());
        }
    }
}
