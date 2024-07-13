package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Classe che implementa l'osservatore dell'inventario
 * 
 */
public class InventoryObserver implements GameObserver {

    /**
     * Metodo che viene chiamato per aggiornare l'osservatore
     * 
     * @param description La descrizione del gioco
     * @param parserOutput L'output del parser
     * @return Un messaggio che descrive lo stato dell'inventario
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput) {
        StringBuilder msg = new StringBuilder();  // Crea un oggetto StringBuilder per costruire il messaggio
        if (parserOutput.getCommand().getType() == CommandType.INVENTORY) {  // Controlla se il comando è di tipo INVENTORY
            if (description.getInventory().isEmpty()) {  // Controlla se l'inventario è vuoto
                msg.append("Il tuo inventario è vuoto!");  // Aggiunge un messaggio che indica che l'inventario è vuoto
            } else {
                msg.append("Nel tuo inventario ci sono:\n");  // Aggiunge un messaggio che indica che ci sono oggetti nell'inventario
                for (AdvObject o : description.getInventory()) {  // Itera attraverso gli oggetti nell'inventario
                    msg.append(o.getName()).append(": ").append(o.getDescription()).append("\n");  // Aggiunge il nome e la descrizione di ogni oggetto al messaggio
                }
            }
        }
        return msg.toString();  // Ritorna il messaggio costruito
    }

}
