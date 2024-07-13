package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.parser.ParserOutput;

/**
 * Interfaccia che rappresenta un osservatore nel contesto di un gioco di avventura.
 * Definisce un metodo per aggiornare l'osservatore con nuove informazioni.
 */
public interface GameObserver {

    /**
     * Metodo che viene chiamato per aggiornare l'osservatore.
     * @param description Descrizione del gioco attuale
     * @param parserOutput Risultato del parsing del comando del giocatore
     * @return Una stringa che rappresenta l'aggiornamento dell'osservatore
     */
    public String update(GameDescription description, ParserOutput parserOutput);

}
