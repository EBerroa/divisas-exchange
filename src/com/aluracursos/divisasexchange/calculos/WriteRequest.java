package com.aluracursos.divisasexchange.calculos;
import com.aluracursos.divisasexchange.modelos.IngresoUsuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteRequest {
    public void guardarJson(ArrayList<IngresoUsuario> data) throws IOException {

        Gson gson  = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        FileWriter escritura = new FileWriter("datosDivisa.json");
        escritura.write(gson.toJson(data));
        escritura.close();
    }
}
