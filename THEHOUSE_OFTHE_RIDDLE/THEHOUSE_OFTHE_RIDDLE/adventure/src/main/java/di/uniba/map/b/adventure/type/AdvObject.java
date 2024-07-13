/*
 * Per cambiare questa intestazione di licenza, scegliere License Headers nel menu delle proprietà del progetto.
 * Per cambiare questo file di template, scegliere Tools | Templates e aprire il template nell'editor.
 */
package di.uniba.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe che rappresenta un oggetto nel gioco di avventura.
 * Contiene le proprietà dell'oggetto e metodi per manipolarle.
 */
public class AdvObject  {

    private final int id; // Identificativo univoco dell'oggettoa

    private String name; // Nome dell'oggetto

    private String description; // Descrizione dell'oggetto
    
    private Set<String> alias; // Alias dell'oggetto

    private boolean openable = false; // Indica se l'oggetto può essere aperto

    private boolean pickupable = true; // Indica se l'oggetto può essere raccolto

    private boolean pushable = false; // Indica se l'oggetto può essere premuto

    private boolean open = false; // Indica se l'oggetto è aperto

    private boolean push = false; // Indica se l'oggetto è premuto
    
    private boolean use = false; //Indica se l'oggetto viene usato 

    /**
     * Costruttore che inizializza l'oggetto con un identificativo.
     *
     * @param id Identificativo dell'oggetto
     */
    public AdvObject(int id) {
        this.id = id;
    }

    /**
     * Costruttore che inizializza l'oggetto con un identificativo e un nome.
     *
     * @param id Identificativo dell'oggetto
     * @param name Nome dell'oggetto
     */
    public AdvObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Costruttore che inizializza l'oggetto con un identificativo, un nome e una descrizione.
     *
     * @param id Identificativo dell'oggetto
     * @param name Nome dell'oggetto
     * @param description Descrizione dell'oggetto
     */
    public AdvObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Costruttore che inizializza l'oggetto con un identificativo, un nome, una descrizione e un insieme di alias.
     *
     * @param id Identificativo dell'oggetto
     * @param name Nome dell'oggetto
     * @param description Descrizione dell'oggetto
     * @param alias Insieme di alias dell'oggetto
     */
    public AdvObject(int id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    /**
     * Restituisce il nome dell'oggetto.
     *
     * @return Nome dell'oggetto
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome dell'oggetto.
     *
     * @param name Nome da impostare
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce la descrizione dell'oggetto.
     *
     * @return Descrizione dell'oggetto
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione dell'oggetto.
     *
     * @param description Descrizione da impostare
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Restituisce se l'oggetto è apribile.
     *
     * @return true se l'oggetto è apribile, false altrimenti
     */
    public boolean isOpenable() {
        return openable;
    }
    
        /**
     * Verifica se l'oggetto è utilizzabile.
     *
     * @return true se l'oggetto è utilizzabile, false altrimenti.
     */
        public boolean isUsable(){
            return use;
        }

        /**
         * Imposta lo stato di utilizzabilità dell'oggetto.
         *
         * @param usable il nuovo stato di utilizzabilità da impostare.
         */
        public void setUsable(boolean usable){
            this.use = usable;
        }

    /**
     * Imposta se l'oggetto è apribile.
     *
     * @param openable true se l'oggetto è apribile, false altrimenti
     */
    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    /**
     * Restituisce se l'oggetto è raccoglibile.
     *
     * @return true se l'oggetto è raccoglibile, false altrimenti
     */
    public boolean isPickupable() {
        return pickupable;
    }

    /**
     * Imposta se l'oggetto è raccoglibile.
     *
     * @param pickupable true se l'oggetto è raccoglibile, false altrimenti
     */
    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    /**
     * Restituisce se l'oggetto è premibile.
     *
     * @return true se l'oggetto è premibile, false altrimenti
     */
    public boolean isPushable() {
        return pushable;
    }

    /**
     * Imposta se l'oggetto è premibile.
     *
     * @param pushable true se l'oggetto è premibile, false altrimenti
     */
    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    /**
     * Restituisce se l'oggetto è aperto.
     *
     * @return true se l'oggetto è aperto, false altrimenti
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Imposta se l'oggetto è aperto.
     *
     * @param open true se l'oggetto è aperto, false altrimenti
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Restituisce se l'oggetto è premuto.
     *
     * @return true se l'oggetto è premuto, false altrimenti
     */
    public boolean isPush() {
        return push;
    }

    /**
     * Imposta se l'oggetto è premuto.
     *
     * @param push true se l'oggetto è premuto, false altrimenti
     */
    public void setPush(boolean push) {
        this.push = push;
    }

    /**
     * Restituisce l'insieme degli alias dell'oggetto.
     *
     * @return Insieme degli alias
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Imposta l'insieme degli alias dell'oggetto.
     *
     * @param alias Insieme degli alias da impostare
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }
    
    /**
     * Imposta l'insieme degli alias dell'oggetto utilizzando un array.
     *
     * @param alias Array degli alias da impostare
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * Restituisce l'identificativo dell'oggetto.
     *
     * @return Identificativo dell'oggetto
     */
    public int getId() {
        return id;
    }

    /**
     * Calcola l'hash code dell'oggetto.
     *
     * @return Hash code dell'oggetto
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    /**
     * Confronta l'oggetto con un altro oggetto per verificarne l'uguaglianza.
     *
     * @param obj L'oggetto da confrontare
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
        final AdvObject other = (AdvObject) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
