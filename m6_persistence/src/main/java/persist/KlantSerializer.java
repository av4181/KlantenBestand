package persist;

import model.Klanten;

import java.io.*;

// Opdracht 2.2
public class KlantSerializer {
    private final String FILENAME;

    public KlantSerializer(String filename) {
        FILENAME = filename;
    }

    public void serialize(Klanten klanten) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(klanten);
    }
    public Klanten deserialize() throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(FILENAME);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Klanten klanten = (Klanten) objectInputStream.readObject();
        System.out.println("deserializing");
        klanten.sorteerOpAanmaakDatum().forEach(System.out::println);
        return klanten;
    }
}
