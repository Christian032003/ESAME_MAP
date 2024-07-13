package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.type.AdvObject;
import java.util.List;

/**
 * Classe di utilità per il gioco di avventura.
 * Fornisce metodi statici per operazioni comuni.
 */
public class GameUtils {

    /**
     * Restituisce un oggetto dall'inventario basato sul suo id.
     * @param inventory Lista degli oggetti nell'inventario
     * @param id Identificativo dell'oggetto da cercare
     * @return Oggetto con l'id specificato se trovato, null altrimenti
     */
    public static AdvObject getObjectFromInventory(List<AdvObject> inventory, int id) {
        for (AdvObject o : inventory) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

}
