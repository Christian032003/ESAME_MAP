package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Classe che rappresenta un contenitore di oggetti nel gioco di avventura.
 * Estende la classe AdvObject e permette di contenere altri oggetti AdvObject.
 * 
 */
public class AdvObjectContainer extends AdvObject {

    private List<AdvObject> list = new ArrayList<>(); // Lista di oggetti contenuti nel contenitore

    /**
     * Costruttore che inizializza il contenitore con un identificativo.
     *
     * @param id Identificativo del contenitore
     */
    public AdvObjectContainer(int id) {
        super(id);
    }

    /**
     * Costruttore che inizializza il contenitore con un identificativo e un nome.
     *
     * @param id Identificativo del contenitore
     * @param name Nome del contenitore
     */
    public AdvObjectContainer(int id, String name) {
        super(id, name);
    }

    /**
     * Costruttore che inizializza il contenitore con un identificativo, un nome e una descrizione.
     *
     * @param id Identificativo del contenitore
     * @param name Nome del contenitore
     * @param description Descrizione del contenitore
     */
    public AdvObjectContainer(int id, String name, String description) {
        super(id, name, description);
    }

    /**
     * Costruttore che inizializza il contenitore con un identificativo, un nome, una descrizione e un insieme di alias.
     *
     * @param id Identificativo del contenitore
     * @param name Nome del contenitore
     * @param description Descrizione del contenitore
     * @param alias Insieme di alias del contenitore
     */
    public AdvObjectContainer(int id, String name, String description, Set<String> alias) {
        super(id, name, description, alias);
    }

    /**
     * Restituisce la lista degli oggetti contenuti nel contenitore.
     *
     * @return Lista degli oggetti contenuti
     */
    public List<AdvObject> getList() {
        return list;
    }

    /**
     * Imposta la lista degli oggetti contenuti nel contenitore.
     *
     * @param list Lista degli oggetti da impostare
     */
    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    /**
     * Aggiunge un oggetto alla lista degli oggetti contenuti nel contenitore.
     *
     * @param o Oggetto da aggiungere
     */
    public void add(AdvObject o) {
        list.add(o);
    }

    /**
     * Rimuove un oggetto dalla lista degli oggetti contenuti nel contenitore.
     *
     * @param o Oggetto da rimuovere
     */
    public void remove(AdvObject o) {
        list.remove(o);
    }

}
