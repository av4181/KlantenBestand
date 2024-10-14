import data.Data;
import model.Klant;
import model.KlantType;
import model.Klanten;
import util.KlantFunctions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Demo_4 {
    public static void main(String[] args) {
        //Nieuwe multiklasse
        Klanten klanten = new Klanten();
        //Data init
        List<Klant> klantData = Data.getData();
        //Klanten toevoegen aan de multiklasse instantie
        klantData.forEach(klanten::voegToe);
        /* Sorteren oude manier

        data = klanten.sorteerOpType();
        System.out.println("Klanten gesorteerd op type:");
        for (Klant klant : data) {
            System.out.println(klant);
        }
        System.out.println("test");

        data = klanten.sorteerOpBtw();
        System.out.println("Klanten gesorteerd op btw:");
        for (Klant klant : data) {
            System.out.println(klant);
        }
        System.out.println();

        data = klanten.sorteerOpAanmaakDatum();
        System.out.println("Klanten gesorteerd op aanmaakdatum:");
        for (Klant klant : data) {
            System.out.println(klant);
        }
        System.out.println();
         */
        //Opdracht 2.2 Sorteren nieuwe manier
        System.out.println("\nKlanten gesorteerd op type:");
        klanten.sortedBy(Klant::getType).forEach(System.out::println);

        System.out.println("\nKlanten gesorteerd op BTW:");
        klanten.sortedBy(Klant::getBtw).forEach(System.out::println);

        System.out.println("\nKlanten gesorteerd op aanmaakdatum:");
        klanten.sortedBy(Klant::getAanmaakDatum).forEach(System.out::println);
        //Opdracht 2.4
        // Filter klanten op klanttype PARTICULIER
        List<Klant> particuliereKlanten = KlantFunctions.filteredList(klantData, klant -> klant.getType() == KlantType.PARTICULIER);
        particuliereKlanten.forEach(System.out::println);
        // Filter klanten op BTW groter dan 0.1
        List<Klant> klantenMetHogeBtw = KlantFunctions.filteredList(klantData, klant -> klant.getBtw() > 0.1);
        System.out.println("\n");
        klantenMetHogeBtw.forEach(System.out::println);
        // Filter klanten op aanmaakdatum na 1 januari 2000
        List<Klant> recenteKlanten = KlantFunctions.filteredList(klantData, klant -> klant.getAanmaakDatum().isAfter(LocalDate.of(2000, 1, 1)));
        System.out.println("\n");
        recenteKlanten.forEach(System.out::println);
        //Opdracht 2.6
        // Bereken het gemiddelde BTW van alle klanten
        double gemiddeldeBtw = KlantFunctions.average(klantData, Klant::getBtw);
        System.out.printf("Gemiddelde BTW: %.2f\n", gemiddeldeBtw);
        // Bereken de gemiddelde leeftijd van alle klanten (in jaren)
        double gemiddeldeLeeftijd = KlantFunctions.average(klantData, klant -> (double) ChronoUnit.YEARS.between(klant.getAanmaakDatum(), LocalDate.now()));
        System.out.printf("Gemiddelde leeftijd: %.1f jaar\n", gemiddeldeLeeftijd);
        //Opdracht 2.8
        // Tel het aantal klanten met klanttype LEVERANCIER
        long aantalBedrijfKlanten = KlantFunctions.countIf(klantData, klant -> klant.getType() == KlantType.LEVERANCIER);
        System.out.printf("Aantal bedrijfsklanten: %d\n", aantalBedrijfKlanten);
        // Tel het aantal klanten met een BTW van 0.21
        long aantalKlantenMetBtw021 = KlantFunctions.countIf(klantData, klant -> klant.getBtw() == 0.21);
        System.out.printf("Aantal klanten met BTW 0.21: %d\n", aantalKlantenMetBtw021);
        //Streams
        System.out.println("\nStream oefeningen:");
        // 3.1 Tel hoeveel klanten zijn aangemaakt na 1 januari 2000
        long aantalNa2000 = klantData.stream()
                .filter(klant -> klant.getAanmaakDatum().isAfter(LocalDate.of(2000, 1, 1)))
                .count();
        System.out.println("Aantal klanten aangemaakt na 1 januari 2000: " + aantalNa2000);
        // 3.2 Sorteer klanten op aanmaakdatum en dan op BTW, en print ze af
        System.out.println("\nKlanten gesorteerd op aanmaakdatum en dan op BTW:");
        klantData.stream()
                .sorted(Comparator.comparing(Klant::getAanmaakDatum).thenComparing(Klant::getBtw))
                .forEach(System.out::println);
        // 3.3 Zet de achternaam om naar hoofdletters, verwijder dubbels, sorteer in aflopende volgorde,
        // en voeg ze samen in een string
        String achternamen = klantData.stream()
                .map(klant -> klant.getAchternaam().toUpperCase())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(", "));
        System.out.println("\nAchternamen: " + achternamen);
        // 3.4 Filter klanten op klanttype PARTICULIER en selecteer er willekeurig één
        Optional<Klant> willekeurigeParticulier = klantData.stream()
                .filter(klant -> klant.getType() == KlantType.PARTICULIER)
                .findAny();
        willekeurigeParticulier.ifPresent(klant -> System.out.println("\nWillekeurige particuliere klant: " + klant));
        // 3.5 geef 2 kampioenen
        List<Klant> klantenMetHoogsteBtw = klantData.stream()
                .sorted(Comparator.comparing(Klant::getBtw).reversed())
                .limit(2)
                .toList();
        System.out.println("Klanten met de hoogste BTW:\n");
        klantenMetHoogsteBtw.forEach(System.out::println);
        // 3.6 Filter klanten op achternaam "Janssens", map naar voornaam, sorteer, en verzamel in een lijst
        List<String> voornamenJanssens = klantData.stream()
                .filter(klant -> klant.getAchternaam().equals("Janssens"))
                .map(Klant::getVoornaam)
                .sorted()
                .toList();
        System.out.println("\nVoornamen van klanten met achternaam Janssens: " + voornamenJanssens);
        // 3.7 Sorteer klanten op aanmaakdatum en partitioneer in twee groepen, ik neem voor en na 2000
        Map<Boolean, List<Klant>> klantenVoorEnNa2000 = klantData.stream()
                .collect(Collectors.partitioningBy(klant -> klant.getAanmaakDatum().isBefore(LocalDate.of(2000, 1, 1))));
        System.out.println("\nKlanten aangemaakt voor 2000:");
        klantenVoorEnNa2000.get(true).forEach(System.out::println);
        System.out.println("\nKlanten aangemaakt na 2000:");
        klantenVoorEnNa2000.get(false).forEach(System.out::println);
        // 3.8 Groepeer klanten op klanttype en sorteer op naam binnen elke groep van type
        Map<KlantType, List<Klant>> klantenPerType = klantData.stream()
                .collect(Collectors.groupingBy(Klant::getType));
        System.out.println("\nKlanten per type:");
        klantenPerType.forEach((type, klantenLijst) -> {
            System.out.println(type + ":");
            klantenLijst.stream()
                    .sorted(Comparator.comparing(Klant::getVoornaam))
                    .forEach(System.out::println);
        });
        //Opdracht 4.3 & 4.4
        // Test verwijder()
        String achternaamTeVerwijderen = "Janssens";
        String voornaamTeVerwijderen = "Peter";
        boolean verwijderd = klanten.verwijder(achternaamTeVerwijderen, voornaamTeVerwijderen);
        System.out.println("\nKlant verwijderd: " + verwijderd);
        // Test zoek()
        String achternaamTeZoeken = "de Vries";
        String voornaamTeZoeken = "Jan";
        Klant gevondenKlant = klanten.zoek(achternaamTeZoeken, voornaamTeZoeken);
        System.out.println("\nGevonden klant: " + gevondenKlant);
    }
}
