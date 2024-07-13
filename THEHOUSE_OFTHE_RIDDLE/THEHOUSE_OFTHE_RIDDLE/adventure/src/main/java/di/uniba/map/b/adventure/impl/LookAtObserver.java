package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Classe che implementa l'osservatore per il comando "look at" (osserva)
 */
public class LookAtObserver implements GameObserver {

    /**
     * Metodo che viene chiamato per aggiornare l'osservatore
     * 
     * @param description La descrizione del gioco
     * @param parserOutput L'output del parser
     * @return Un messaggio che descrive l'osservazione della stanza corrente
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput) {
        // Crea un oggetto StringBuilder per costruire il messaggio
        StringBuilder msg = new StringBuilder();
        
            // Controlla se il comando è di tipo LOOK_AT
            if (parserOutput.getCommand().getType() == CommandType.LOOK_AT) {
                // Controlla se la stanza corrente ha una descrizione di "look"
                if (description.getCurrentRoom().getLook() != null && description.getCurrentRoom().isVisible() == true) {
                    // Aggiunge la descrizione di "look" della stanza al messaggio
                    msg.append(description.getCurrentRoom().getLook());
                } else {
                    // Aggiunge un messaggio di default se non c'è niente di interessante nella stanza
                    msg.append("Non c'e' niente di interessante qui.");
                }
            }
        // Ritorna il messaggio costruito
        return msg.toString();
    }
}
