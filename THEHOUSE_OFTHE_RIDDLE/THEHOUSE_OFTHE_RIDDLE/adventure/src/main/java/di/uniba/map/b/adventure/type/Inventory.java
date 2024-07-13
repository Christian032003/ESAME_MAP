package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta l'inventario nel gioco di avventura.
 * Consente di gestire una lista di oggetti AdvObject.
 */
public class Inventory {

    private List<AdvObject> list = new ArrayList<>(); // Lista degli oggetti nell'inventario

    /**
     * Restituisce la lista degli oggetti nell'inventario.
     * 
     * @return Lista degli oggetti nell'inventario
     */
    public List<AdvObject> getList() {
        return list;
    }

    /**
     * Imposta la lista degli oggetti nell'inventario.
     * 
     * @param list Lista degli oggetti da impostare
     */
    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    /**
     * Aggiunge un oggetto alla lista dell'inventario.
     * 
     * @param o Oggetto da aggiungere
     */
    public void add(AdvObject o) {
        list.add(o);
    }

    /**
     * Rimuove un oggetto dalla lista dell'inventario.
     * 
     * @param o Oggetto da rimuovere
     */
    public void remove(AdvObject o) {
        list.remove(o);
    }
}
