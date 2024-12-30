package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReflectionTools {

    /**
     * Analyseert een of meerdere klassen en print informatie over elke klasse
     * @param aClassArray Een variabel aantal klassen die geanalyseerd moeten worden.
     * De drie puntjes (...) geven aan dat deze methode een varargs parameter accepteert,
     * waardoor je nul, één of meerdere klassen als argument kunt meegeven.
     *
     * Alle methodes zijn static gemaakt.  ReflectionTools klasse is niet bedoeld om veelvuldig
     *                    instanties van te maken.  Vandaar methode via static meteen beschikbaar maken
     */
    public static void classAnalysis(Class<?>... aClassArray) {
        for (Class<?> aClass : aClassArray) {
            System.out.println(analyseerKlasse(aClass));
        }
    }

    /**
     * Hoofdmethode die een klasse analyseert.  Returned een string met info over de klasse.
     * Gebruikt de extractMethods methode of onderscheid tussen getters, setters en andere methodes te maken.
     * Gebruikt een apart buildString methode om de output te formatteren.
     * Andere gebruikte methodes komen recht uit de Class klasse.
     * @param aClass De klasse die geanalyseerd moet worden.
     * @return Een stringbuilder object met informatie over de klasse, inclusief de naam, superklasse,
     *         interfaces, constructors, attributen en methoden.
     */
    private static String analyseerKlasse(Class<?> aClass) {
        StringBuilder output = new StringBuilder();

        output.append(String.format("Analyse van de klasse: %s\n", aClass.getSimpleName()));
        output.append("======================================================================\n");
        output.append(String.format("%-25s  : %s\n", "Fully qualified name", aClass.getName()));
        output.append(String.format("%-25s  : %s\n", "Naam an de superklasse", aClass.getSuperclass().getName()));
        output.append(String.format("%-25s  : %s\n", "Naam van de package", aClass.getPackageName()));

        // Interfaces
        Class<?>[] interfaces = aClass.getInterfaces();
        output.append(String.format("%-25s  : %s\n", "Interfaces", interfaces.length > 0
                ? Arrays.stream(interfaces).map(Class::getSimpleName).reduce("", (a, b) -> a + b + " ")
                : "Geen"));

        // Constructors
        output.append(String.format("%-25s  : %s\n", "Constructors", buildString(Arrays.stream(aClass.getDeclaredConstructors())
                .map(Constructor::toGenericString).toList())));

        // Attributen met modifiers
        output.append(String.format("%-25s  : %s\n", "Attributen", buildString(Arrays.stream(aClass.getDeclaredFields())
                .map(field -> Modifier.toString(field.getModifiers()) + " " + field.getName() + "(" + field.getType().getSimpleName() + ")")
                .toList())));

        // Getters en setters
        output.append(String.format("%-25s  : %s\n", "Getters", extractMethods(aClass, "get")));
        output.append(String.format("%-25s  : %s\n", "Setters", extractMethods(aClass, "set")));
        output.append(String.format("%-25s  : %s\n", "andere methodes", extractMethods(aClass, "")));

        return output.toString();
    }

    /**
     * Methode om specifieke methoden (getters, setters, etc.) te extraheren.  Dus bv. enkel de getters
     * Deze methode vindt echter alle methodes waar 'get' of 'set' in voorkomt.
     * Dus ook diegene die in feite geen getters of setters van attributen zijn.
     * @param aClass De klasse waarvan de methoden geëxtraheerd moeten worden.
     * @param prefix Prefix van de methodenamen "get", "set" of "" voor de resterende methodes.
     * @return Een geformatteerde string met de namen van de geëxtraheerde methoden.
     */
    private static String extractMethods(Class<?> aClass, String prefix) {
        List<String> methodList = new ArrayList<>();
        for (Method method : aClass.getDeclaredMethods()) {
            if (method.getName().startsWith(prefix)) {
                if ((prefix.equals("get") && method.getReturnType() != void.class) ||
                        (prefix.equals("set") && method.getParameterCount() == 1)) {
                    methodList.add(method.getName());
                }
            }
        }
        return buildString(methodList);
    }

    /**
     * Maakt een nieuwe instantie van de gegeven klasse aClass en roept alle methoden aan die geannoteerd zijn met
     * `@CanRun` en een String-parameter accepteren.
     * @param aClass De klasse waarvan een instantie moet worden gemaakt.
     * @return Het geïnstantieerde object, nadat de geannoteerde methoden zijn aangeroepen.
     */
    public static Object runAnnotated(Class<?> aClass) throws Exception {
        System.out.println("Instantieer een nieuw object van de klasse: " + aClass.getName());
        Object object = aClass.getDeclaredConstructor().newInstance();

        boolean canRunFound = false;

        for (Method method : aClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CanRun.class) && method.getParameterCount() == 1 &&
                    method.getParameterTypes()[0].equals(String.class)) {
                canRunFound = true;
                method.invoke(object, method.getAnnotation(CanRun.class).value());
            }
        }

        if (canRunFound) {
            return object;
        } else {
            throw new NoSuchMethodException("Deze klasse bevat geen methode geannoteerd met @Canrun " +
                    "en een String parameter");
        }
    }

    private static String buildString(List<String> inputList) {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (String s : inputList) {
            stringBuilder
                    .append("\t\t\t\t")
                    .append(s)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
