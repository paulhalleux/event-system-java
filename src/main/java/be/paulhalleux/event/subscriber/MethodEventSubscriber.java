package be.paulhalleux.event.subscriber;

import be.paulhalleux.event.Event;
import be.paulhalleux.event.EventHandler;
import be.paulhalleux.event.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implémentation d'un souscripteur d'écouteur d'évènements
 * utilisant une map qui associe directement les méthodes correspondant
 * a des action d'évènement à leur listener.
 */
public class MethodEventSubscriber implements IEventSubscriber {

    /**
     * Utilisation d'une map permettant d'associer à un listener
     * toutes les méthodes d'évent qu'il contient pour éviter de devoir
     * les rechercher à chaque appel d'un event. Elle permet aussi d'éviter
     * les doublons.
     */
    private final Map<Listener, Method[]> subscribed;

    /**
     * Constructeur du subscriber.
     */
    public MethodEventSubscriber() {
        this.subscribed = new LinkedHashMap<>();
    }

    @Override
    public boolean subscribe(final Listener listener) {
        Method[] methods = getHandleableMethods(listener.getClass());
        if (methods.length >= 1) {
            subscribed.put(listener, getHandleableMethods(listener.getClass()));
            return true;
        }
        return false;
    }

    @Override
    public void call(final Event event) {
        // Pour chaque listener contenant des méthodes abonné.
        for (var entry : subscribed.entrySet()) {
            // On appelle les méthodes du listener.
            handleMethodsInvoke(entry.getKey(), entry.getValue(), event);
        }
    }

    /**
     * Méthode gérant l'appel des méthodes concernée par l'appel.
     *
     * @param listener Listener sur lequel on appel les méthodes.
     * @param methods  Méthodes à appeler.
     * @param event    Event appelé.
     */
    private void handleMethodsInvoke(final Listener listener, final Method[] methods, final Event event) {
        for (Method method : methods) {
            if (!hasEventAsParameter(method, event)) continue;
            try {
                // On appelle la méthode depuis la classe listener avec le
                // paramètre event
                method.invoke(listener, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Permet de filtrer les méthodes exécutables.
     *
     * @param listener Listener contenant les méthodes.
     * @return Une tableau de méthodes.
     */
    private Method[] getHandleableMethods(final Class<? extends Listener> listener) {
        // Crée une liste avec les méthodes publiques, annotée avec @EventHandler
        // et ne possédant qu'un seul paramètre.
        // Retourne la liste convertie en tableau
        // car je n'ai pas besoin des méthodes fournie par
        // une collection.
        // le .toArray(Method[]::new) à été généré par intellij
        // il génère un tableau du type donné depuis le stream.
        return Arrays.stream(listener.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers())
                        && m.isAnnotationPresent(EventHandler.class)
                        && m.getParameterCount() == 1).toArray(Method[]::new);
    }

    /**
     * Méthode permettant de savoir si un listener concerne un évènement donné
     * grace à la liste de ses paramètre.
     *
     * @param method Méthode listener.
     * @param event  Évènement recherché.
     * @return true si la méthode concerne l'évènement.
     */
    private boolean hasEventAsParameter(final Method method, final Event event) {
        // Je retourne true si le le premier argument de la méthode correspond
        // au type de l'évent demandé.
        return method.getParameterTypes()[0].getSimpleName().equalsIgnoreCase(event.getEventName());
    }

}