import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IntegrationSystem {
    public static void main(String[] args) {
        Path arquivoBacen = Paths.get("/Users/kauemagnus/Documents/BacenIntegration/bacen-cadastros.csv");

        try {

            // DOMINAR AS PRINCIPAIS CLASSES DO JAVA:
            // Integer, Double, BigDecimal, FIle, Files, Path, Paths
            // Math, String, StringBuilder

            List<String> cadastros = Files.readAllLines(arquivoBacen);
            System.out.println("Linhas obtidas: " + cadastros.size());
            List<Cadastro> cadastroObjetos = new ArrayList<>();

            // Métodos: nome + parametros + tipo de retorno
            for (String cadastro: cadastros) {
                String [] campos = cadastro.split(";");


                if (campos.length == 5) {
                    Cadastro cadastroObjeto = new Cadastro();

                    try {
                        cadastroObjeto.codigo = Integer.parseInt(campos [0]);
                        cadastroObjeto.nome = campos [1];
                        cadastroObjeto.sexo = campos [2];
                        cadastroObjeto.rendaMinima = Double.parseDouble(campos [3]);
                        cadastroObjeto.restricao = campos[4].equals("T") ? true : false;

                        cadastroObjetos.add(cadastroObjeto);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro na conversão dos dados para cadastro: " + cadastro);
                    }
                } else {
                    System.err.println("Formato inválido para cadastro: " + cadastro);
                }

            }
            System.out.println("Finalizando o processo de conversão.");

        } catch (IOException e) {
            System.err.println("Algum erro aconteceu! " + e.getMessage());
        }


    }
}
