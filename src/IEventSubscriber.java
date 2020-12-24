/**
 * Interface d'un event subscriber qui gère l'abonnement
 * des différents évènements à celui-ci.
 */
public interface IEventSubscriber {

    /**
     * Méthode permettant d'abonner une classe contenant une ou plusieurs
     * méthode gérant une action a effectuer lors d'un évènement.
     *
     * @param listener Classe à abonner.
     * @return true si la classe à été abonnée, sinon false.
     */
    boolean subscribe(Listener listener);

    /**
     * Méthode permettant d'appeler toutes les méthodes associée à
     * un évènement.
     *
     * @param event Évènement appelé.
     */
    void call(Event event);

}
