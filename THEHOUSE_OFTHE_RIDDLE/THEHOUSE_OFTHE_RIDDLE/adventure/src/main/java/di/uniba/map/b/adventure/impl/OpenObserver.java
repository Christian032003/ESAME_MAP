package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.CommandType;
import java.util.Iterator;
import di.uniba.map.b.adventure.GameObserver;

/**
 * La classe OpenObserver implementa l'interfaccia GameObserver e gestisce 
 * il comando di apertura degli oggetti nel gioco.
 * (OPEN - apre gli oggetti)
 */
public class OpenObserver implements GameObserver {

    /**
     * Questo metodo viene chiamato per aggiornare lo stato del gioco in base all'input del parser.
     * 
     * @param description La descrizione attuale del gioco.
     * @param parserOutput L'output del parser che contiene il comando e gli oggetti coinvolti.
     * @return Un messaggio che descrive il risultato dell'operazione di apertura.
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput) {
        StringBuilder msg = new StringBuilder();  
        
        // Verifica se il comando è di tipo OPEN
        if (parserOutput.getCommand().getType() == CommandType.OPEN) {
            AdvObject targetObject = parserOutput.getObject();

            // Se non c'è nessun oggetto da aprire
            if (targetObject == null) {
                msg.append("Non c'è niente da aprire qui.");
            } else {
                // Verifica se l'oggetto è nella stanza corrente
                AdvObject roomObject = description.getCurrentRoom().getObject(targetObject.getId());

                // Se l'oggetto è nella stanza e può essere aperto
                if (roomObject != null && roomObject.isOpenable()) {
                    // Gestisce l'apertura di un contenitore
                    if (roomObject instanceof AdvObjectContainer) {
                        AdvObjectContainer container = (AdvObjectContainer) roomObject;

                        // Se il contenitore non è già aperto
                        if (!container.isOpen()) {
                            msg.append("Hai aperto: ").append(container.getName()).append("\n");

                            // Se il contenitore non è vuoto
                            if (!container.getList().isEmpty()) {
                                msg.append(container.getName()).append(" contiene:");
                                Iterator<AdvObject> it = container.getList().iterator();

                                // Itera sugli oggetti nel contenitore
                                while (it.hasNext()) {
                                    AdvObject next = it.next();
                                    description.getInventory().add(next); // Aggiunge l'oggetto all'inventario
                                    msg.append(" ").append(next.getName());
                                    msg.append("\n Hai raccolto:").append(next.getName());
                                    next.setUsable(true); // Imposta l'oggetto come utilizzabile
                                    it.remove(); // Rimuove l'oggetto dal contenitore
                                }
                                msg.append("\n");
                            }
                            container.setOpen(true); // Imposta il contenitore come aperto
                        } else {
                            msg.append("Il contenitore è già aperto.");
                        }
                    } else {
                        // Se l'oggetto non è un contenitore, semplicemente lo apre
                        msg.append("Hai aperto: ").append(roomObject.getName()).append("\n");
                        int currentRoomId = description.getCurrentRoom().getId();
                        int objectId = roomObject.getId();

                        // Controlla se l'oggetto aperto è la tenda nella stanza 2
                        if (currentRoomId == 2 && objectId == 12) {
                            msg.append("Aprendo la tenda, vedi che la casa all'esterno ha un giardino.\n"
                                    + "Forse sarà quella la via di fuga?");
                        }
                        // Controlla se l'oggetto aperto è l'armadio nella stanza 5
                        if (currentRoomId == 5 && objectId == 13) {
                            msg.append("Aprendo l'armadio, apparentemente non vedi nulla di utile....");
                        }
                        roomObject.setOpen(true); // Imposta l'oggetto come aperto
                    }
                } else {
                    msg.append("Non puoi aprire questo oggetto.");
                }
            }
        }

        return msg.toString();  

    }
}
