/**
 * Classe abstraite représentant un event.
 * Elle permet de récupérer le nom de l'évent (nom de la class)
 * afin de détecter les méthodes (@EventHandler) à appeler lors
 * d'un callEvent.
 */
public abstract class Event {

    /**
     * Méthode permettant de récupérer le nom de l'évènement.
     * @return Le nom de la classe de l'évènement.
     */
    public String getEventName(){
        return getClass().getSimpleName();
    }

}
