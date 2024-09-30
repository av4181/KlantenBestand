import data.Data;
import generics.PriorityQueue;

import java.util.Random;

public class Demo_2 {

    public static void main(String[] args) {
        //Deel 2.6 van de opdracht
        PriorityQueue<String> myQueue = new PriorityQueue<>();
        myQueue.enqueue("Tokio", 2);
        myQueue.enqueue("Denver", 5);
        myQueue.enqueue("Rio", 2);
        myQueue.enqueue("Oslo", 3);
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(myQueue.toString());
        System.out.println("aantal: " + myQueue.getSize());
        System.out.println("positie van Tokio: " + myQueue.search("Tokio"));
        System.out.println("positie van Nairobi: " + myQueue.search("Nairobi"));
        for(int i = 0; i < 4; i++) { System.out.println("Dequeue: " + myQueue.dequeue()); }
        System.out.println("Size na dequeue: " + myQueue.getSize());

        //Deel 2.7 van de opdracht
        var klantPriorityQueue = new PriorityQueue<>();
        var random = new Random();
        var klanten = Data.getData();
        for (var klant : Data.getData()) {
            klantPriorityQueue.enqueue(klant, random.nextInt(5) + 1);
        }

        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(klantPriorityQueue.toString());
        System.out.println("aantal klanten: " + klantPriorityQueue.getSize());
        System.out.println("positie van " + klanten.get(0).getVoornaam() + " " + klanten.get(0).getAchternaam() + ": "
                + klantPriorityQueue.search(klanten.get(0)));
        System.out.println("positie van " + klanten.get(3).getVoornaam() + " " + klanten.get(3).getAchternaam() + ": "
                + klantPriorityQueue.search(klanten.get(3)));
        for(int i = 0; i < 4; i++) {

            System.out.println("Dequeue: " + klantPriorityQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + klantPriorityQueue.getSize());

    }
}
