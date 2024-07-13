package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta una Stanza nel gioco di avventura.
 * Ogni stanza ha un identificativo, un nome, una descrizione, un aspetto, e può contenere oggetti.
 * Può anche avere collegamenti ad altre stanze in diverse direzioni (nord, sud, est, ovest).
 * Inoltre, una stanza può essere visibile o invisibile.
 * 
 */
public class Room {

    // Identificativo unico della stanza
    private  int id;

    // Nome della stanza
    private String name;

    // Descrizione della stanza
    private String description;

    // Aspetto della stanza (testo visualizzato quando si osserva la stanza)
    private String look;

    // Visibilità della stanza (true se visibile, false se invisibile)
    private boolean visible = true;

    // Riferimenti alle stanze adiacenti
    private Room south = null;
    private Room north = null;
    private Room east = null;
    private Room west = null;

    // Lista degli oggetti presenti nella stanza
    private final List<AdvObject> objects = new ArrayList<>();

    /**
     * Costruttore che crea una stanza con un identificativo, un nome e una descrizione specificati.
     *
     * @param id Identificativo della stanza
     * @param name Nome della stanza
     * @param description Descrizione della stanza
     */
    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
       
    }

    /**
     * Restituisce il nome della stanza.
     *
     * @return Nome della stanza
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome della stanza.
     *
     * @param name Nome della stanza
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce la descrizione della stanza.
     *
     * @return Descrizione della stanza
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione della stanza.
     *
     * @param description Descrizione della stanza
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Restituisce se la stanza è visibile.
     *
     * @return true se la stanza è visibile, false altrimenti
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Imposta la visibilità della stanza.
     *
     * @param visible true se la stanza deve essere visibile, false altrimenti
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Restituisce la stanza a sud di questa stanza.
     *
     * @return Stanza a sud
     */
    public Room getSouth() {
        return south;
    }

    /**
     * Imposta la stanza a sud di questa stanza.
     *
     * @param south Stanza a sud
     */
    public void setSouth(Room south) {
        this.south = south;
    }

    /**
     * Restituisce la stanza a nord di questa stanza.
     *
     * @return Stanza a nord
     */
    public Room getNorth() {
        return north;
    }

    /**
     * Imposta la stanza a nord di questa stanza.
     *
     * @param north Stanza a nord
     */
    public void setNorth(Room north) {
        this.north = north;
    }

    /**
     * Restituisce la stanza a est di questa stanza.
     *
     * @return Stanza a est
     */
    public Room getEast() {
        return east;
    }

    /**
     * Imposta la stanza a est di questa stanza.
     *
     * @param east Stanza a est
     */
    public void setEast(Room east) {
        this.east = east;
    }

    /**
     * Restituisce la stanza a ovest di questa stanza.
     *
     * @return Stanza a ovest
     */
    public Room getWest() {
        return west;
    }

    /**
     * Imposta la stanza a ovest di questa stanza.
     *
     * @param west Stanza a ovest
     */
    public void setWest(Room west) {
        this.west = west;
    }

    /**
     * Restituisce la lista degli oggetti presenti nella stanza.
     *
     * @return Lista degli oggetti nella stanza
     */
    public List<AdvObject> getObjects() {
        return objects;
    }

    /**
     * Restituisce l'identificativo della stanza.
     *
     * @return Identificativo della stanza
     */
    public int getId() {
        return id;
    }
    
   /**
     * Imposta l'identificativo della stanza.
     *
     * @param id Nuovo identificativo da impostare
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Calcola il codice hash della stanza basato sul suo identificativo.
     *
     * @return Codice hash della stanza
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    /**
     * Confronta questa stanza con un altro oggetto per verificarne l'uguaglianza.
     *
     * @param obj Oggetto da confrontare
     * @return true se gli oggetti sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * Restituisce l'aspetto della stanza (testo visualizzato quando si osserva la stanza).
     *
     * @return Aspetto della stanza
     */
    public String getLook() {
        return look;
    }

    /**
     * Imposta l'aspetto della stanza (testo visualizzato quando si osserva la stanza).
     *
     * @param look Aspetto della stanza
     */
    public void setLook(String look) {
        this.look = look;
    }

    /**
     * Restituisce un oggetto nella stanza dato il suo identificativo.
     *
     * @param id Identificativo dell'oggetto
     * @return Oggetto con l'identificativo specificato, oppure null se non trovato
     */
    public AdvObject getObject(int id) {
        for (AdvObject o : objects) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }
   
   
}
