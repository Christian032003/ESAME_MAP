package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Implementazione di un osservatore che gestisce il comando di raccolta oggetti.
 * (PICK UP - prende gli oggetti)
 * 
 */
public class PickUpObserver implements GameObserver {

    /**
     * Aggiorna lo stato del gioco in base all'output del parser.
     *
     * @param description La descrizione attuale del gioco
     * @param parserOutput L'output del parser contenente il comando e l'oggetto
     * @return Un messaggio che descrive il risultato dell'azione
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput) {
        StringBuilder msg = new StringBuilder();
        
        // Controlla se il comando è di tipo PICK_UP (raccogli)
        if (parserOutput.getCommand().getType() == CommandType.PICK_UP) {
            // Controlla se l'oggetto specificato nel comando esiste
            if (parserOutput.getObject() != null) {
                // Controlla se l'oggetto può essere raccolto
                if (parserOutput.getObject().isPickupable()) {
                    // Aggiunge l'oggetto all'inventario
                    description.getInventory().add(parserOutput.getObject());
                    // Rimuove l'oggetto dalla stanza corrente
                    description.getCurrentRoom().getObjects().remove(parserOutput.getObject());
                    msg.append("Hai raccolto: ").append(parserOutput.getObject().getDescription());
                    
                    // Controlla se la stanza è vuota dopo la rimozione dell'oggetto
                    if (description.getCurrentRoom().getObjects().isEmpty()) {
                        // Aggiorna la descrizione della stanza
                        description.getCurrentRoom().setLook("Non c'e' piu' nulla da raccogliere.");
                    }
                    
                } else {
                    // L'oggetto non può essere raccolto
                    msg.append("Non puoi raccogliere questo oggetto.");
                }
            } else {
                // Nessun oggetto specificato per il comando di raccolta
                msg.append("Non c'e' niente da raccogliere qui.");
            }
        }
        return msg.toString();
    }
}
