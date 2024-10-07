import model.Klant;
import model.Klanten;
import model.Persoon;
import reflection.ReflectionTools;

public class Demo_3 {
    public static void main(String[] args) {
        //Opdract 2.4 & 2.5
        ReflectionTools.classAnalysis(Persoon.class, Klant.class, Klanten.class);

        //opdracht 3.5
        try {
            Object annotated = ReflectionTools.runAnnotated(Klant.class);
            System.out.println(annotated);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
