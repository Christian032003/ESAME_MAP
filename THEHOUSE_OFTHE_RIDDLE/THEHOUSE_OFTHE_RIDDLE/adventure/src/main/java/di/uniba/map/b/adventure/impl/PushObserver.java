/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Implementazione di un osservatore che gestisce il comando di pressione oggetti.
 * (PUSH - premere oggetti)
 * 
 */
public class PushObserver implements GameObserver {

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

        // Controlla se il comando è di tipo PUSH (premere)
        if (parserOutput.getCommand().getType() == CommandType.PUSH) {
            // Verifica se l'oggetto da premere è presente nell'inventario o nella stanza corrente
            boolean button = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 11;
            button = button || parserOutput.getObject() != null && parserOutput.getObject().getId() == 11;

            // Cerca se l'oggetto specificato nel comando esiste ed è premibile
            if (button) {
                System.out.println("Premi il pulsante  e in seguito vieni colpito da una serie di frecce velenose...\n"
                        + "Non sei riuscito a schivarle tutte....\n"
                        + "SEI MORTO....\n");
                description.getCurrentRoom().getObject(11).setPush(true);
                
                // Controlla se l'oggetto è stato premuto e termina il gioco
                if (description.getCurrentRoom().getObject(11).isPush() == true) {
                    System.out.println("La tua avventura termina qui! Complimenti!");
                    System.exit(0);
                                                                                  }
                        } 
        else {
                // Messaggio se non ci sono oggetti premibili
                msg.append("Non ci sono oggetti che puoi premere qui.");
            }
        }

        return msg.toString();
    }
}
