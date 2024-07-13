package di.uniba.map.b.adventure;

/**
 * Interfaccia che rappresenta un oggetto osservabile nel contesto di un gioco di avventura.
 * Consente di aggiungere, rimuovere e notificare osservatori.
 */
public interface GameObservable {
    
    /**
     * Aggiunge un osservatore.
     * @param o Osservatore da aggiungere
     */
    public void attach(GameObserver o);
    
    /**
     * Rimuove un osservatore.
     * @param o Osservatore da rimuovere
     */
    public void detach(GameObserver o);
    
    /**
     * Notifica tutti gli osservatori di un cambiamento.
     */
    public void notifyObservers();
}
