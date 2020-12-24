/**
 * Interface applicable à un objet dont
 * une action peut-être annulée.
 */
public interface Cancellable {

    /**
     * Permet de définit à true ou false
     * le status d'annulation de l'objet.
     *
     * @param cancelled boolean.
     */
    void setCancelled(boolean cancelled);

    /**
     * Permet de récupérer le status d'annulation.
     *
     * @return Le status d'annulation.
     */
    boolean isCancelled();

}
