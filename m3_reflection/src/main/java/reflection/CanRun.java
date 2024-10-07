package reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*Maak onder de package reflection een nieuwe annotation met de naam @CanRun.
* Deze annotation zal gebruikt worden boven een methode en moet at runtime beschikbaar zijn. Het is een
* zogenaamde “single-value” annotation, met één attribuut value van het type String. Stel als default-
* value “dummy” in.
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CanRun {
    String value() default "dummy";
}
