import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Permet d'identifier une méthode qui
 * gère une action a effectuer lors d'un appel à
 * un évènement (polymorphisme). Peut potentiellement permettre
 * de donner une priorité a des méthodes, etc.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {}
