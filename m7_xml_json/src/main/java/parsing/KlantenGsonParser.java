package parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Klanten;
import model.LocalDateGsonAdapter;

import java.io.*;
import java.time.LocalDate;

public class KlantenGsonParser {

    //Opdracht 3.1
    public static void writeJson(Klanten klanten, String fileName) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter())
                .create();

        String jsonString = gson.toJson(klanten);
        try (FileWriter jsonWriter = new FileWriter(fileName)) {
            jsonWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Opdracht 3.2
    public static Klanten readJson(String fileName) {
        try (BufferedReader data = new BufferedReader(new FileReader(fileName))) {

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter())
                    .create();
            return gson.fromJson(data, Klanten.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
