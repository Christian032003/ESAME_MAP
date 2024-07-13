package di.uniba.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe che rappresenta un comando nel gioco di avventura.
 * Ogni comando ha un tipo, un nome e un insieme di alias che rappresentano i nomi alternativi del comando.
 * 
 * @author pierpaolo
 */
public class Command {

    private final CommandType type;  // Tipo del comando
    private final String name;       // Nome del comando
    private Set<String> alias;       // Alias del comando

    /**
     * Costruttore che inizializza il comando con il tipo e il nome.
     * 
     * @param type Tipo del comando
     * @param name Nome del comando
     */
    public Command(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Costruttore che inizializza il comando con il tipo, il nome e un insieme di alias.
     * 
     * @param type Tipo del comando
     * @param name Nome del comando
     * @param alias Insieme di alias del comando
     */
    public Command(CommandType type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }

    /**
     * Restituisce il nome del comando.
     * 
     * @return Nome del comando
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce l'insieme degli alias del comando.
     * 
     * @return Insieme degli alias del comando
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Imposta l'insieme degli alias del comando.
     * 
     * @param alias Insieme di alias da impostare
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     * Imposta l'insieme degli alias del comando a partire da un array di stringhe.
     * 
     * @param alias Array di alias da impostare
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * Restituisce il tipo del comando.
     * 
     * @return Tipo del comando
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Restituisce il valore hash del comando.
     * 
     * @return Valore hash del comando
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }

    /**
     * Confronta questo comando con un altro oggetto per verificarne l'uguaglianza.
     * 
     * @param obj Oggetto da confrontare
     * @return true se i comandi sono uguali, false altrimenti
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
        final Command other = (Command) obj;
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
    public boolean isLookCommand() {
    return "guarda".equalsIgnoreCase(name) 
            || "look".equalsIgnoreCase(name) 
            || (alias != null && (alias.contains("guarda") 
            || alias.contains("look")));
}
}
