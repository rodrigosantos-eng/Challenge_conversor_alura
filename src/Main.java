
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
        System.out.println("Por favor, escolha a moeda base para a conversão:");
        var moedaBase = sc.nextLine();
        System.out.println("Agora, escolha a moeda para a qual deseja converter:");
        var moedaAlvo = sc.nextLine();

        String chaveApi = "74590632bd015856d5d59433";
        String url_str = "https://v6.exchangerate-api.com/v6/" + chaveApi + "/pair/" + moedaBase.toLowerCase() + "/" + moedaAlvo.toLowerCase();

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url_str)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject obj = new Gson().fromJson(response.body(), JsonObject.class);
        Double taxaConversao = obj.getAsJsonPrimitive("conversion_rate").getAsDouble();
        System.out.println("A conversão de " + moedaBase + " para " + moedaAlvo + " é: " + taxaConversao);
    }
}
