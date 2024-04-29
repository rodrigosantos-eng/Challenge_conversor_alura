
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException {

            Scanner sc = new Scanner(System.in);
            System.out.println("Bem-vindo ao Conversor de Moedas!");

            while (true) {
                try {

                    System.out.println("Por favor, escolha a moeda base para a conversão:");
                    var moedaBase = sc.nextLine();

                    System.out.println("Agora, escolha a moeda para a qual deseja converter:");
                    var moedaAlvo = sc.nextLine();

                    System.out.println("Digite o valor que deseja converter");
                    double quantidadeBase = Double.parseDouble(sc.nextLine());

                    String chaveApi = "74590632bd015856d5d59433";
                    String url_str = "https://v6.exchangerate-api.com/v6/" + chaveApi + "/pair/" + moedaBase.toLowerCase() + "/" + moedaAlvo.toLowerCase();

                    HttpClient client = HttpClient.newBuilder().build();
                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url_str)).build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    JsonObject obj = new Gson().fromJson(response.body(), JsonObject.class);
                    double taxaConversao = obj.getAsJsonPrimitive("conversion_rate").getAsDouble();
                    System.out.println("Conversão feita com sucesso!!!");
                    double quantidadeAlvo = (quantidadeBase * taxaConversao);
                    System.out.println("Conversão realizada com sucesso!");
                    System.out.println("Convertendo " + quantidadeBase + " " + moedaBase + " para " + moedaAlvo + ", você obteria aproximadamente " + (int) quantidadeAlvo + " " + moedaAlvo + "\n");
                    System.out.println("Deseja continuar? Para encerrar digite N para continuar digite S");
                    var input = sc.nextLine();
                    if(input.equals("N".toLowerCase())){
                        break;
                    }

                } catch (Exception e) {
                    System.out.println("Código de moeda inválido. Utilize os códigos de moeda, por exemplo, \"USD\" para dólar americano.\n");
                }
            }
    }
}
