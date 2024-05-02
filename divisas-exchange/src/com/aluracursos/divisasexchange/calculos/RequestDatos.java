package com.aluracursos.divisasexchange.calculos;
import com.aluracursos.divisasexchange.modelos.EntradaTipoDeCambio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestDatos {

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public EntradaTipoDeCambio requestDatosDeCambio(String divisaActual) {
        EntradaTipoDeCambio datosDivisa = null;

        try {

            URI url = URI.create( "https://v6.exchangerate-api.com/v6/5fe877485d3becd2e93d8e0d/latest/"+divisaActual);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            // Procesa el JSON y se convierte en un objeto EntradaTipoDeCambio
            datosDivisa = gson.fromJson(json, EntradaTipoDeCambio.class);


        } catch (IllegalArgumentException e) {
            System.out.println("Error en la URL!!, por favor verifique la dirección nuevamente...");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return datosDivisa;
    }
}
