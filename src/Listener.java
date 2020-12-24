/**
 * Permet d'identifier une classe qui contient
 * des méthodes à exécuter lors d'un appel d'évènement.
 *
 * J'aurai pu définir une méthode dans cette interface
 * pour ne pas avoir a utiliser la réflexion mais ce n'était
 * pas pratique pour les contrôleurs qui contiennent plusieurs
 * méthodes à appeler lors d'évent.
 */
public interface Listener {}
