package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.Room;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import di.uniba.map.b.adventure.type.AdvObjectContainer;


/**
 * Classe astratta che rappresenta la descrizione di un gioco di avventura.
 * Contiene le stanze, i comandi, l'inventario e la stanza corrente.
 */
public abstract class GameDescription {

    // Lista delle stanze del gioco
    private final List<Room> rooms = new ArrayList<>();

    // Lista dei comandi disponibili nel gioco
    private final List<Command> commands = new ArrayList<>();

    // Lista degli oggetti nell'inventario del giocatore
    private final List<AdvObject> inventory = new ArrayList<>();
    
    private AdvObjectContainer CurrentObjectContainer;
    
    // Username del giocatore
    private String username;

    // Stanza corrente in cui si trova il giocatore
    private Room currentRoom;

    
    public AdvObjectContainer getCurrentObject(){
        return CurrentObjectContainer;
    }
            
    /**
     * Restituisce la lista delle stanze del gioco.
     * @return Lista delle stanze
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Restituisce la lista dei comandi disponibili nel gioco.
     * @return Lista dei comandi
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Restituisce la stanza corrente in cui si trova il giocatore.
     * @return Stanza corrente
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Imposta la stanza corrente in cui si trova il giocatore.
     * @param currentRoom Nuova stanza corrente
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Restituisce la lista degli oggetti nell'inventario del giocatore.
     * @return Lista degli oggetti nell'inventario
     */
    public List<AdvObject> getInventory() {
        return inventory;
    }

    /**
     * Metodo astratto per inizializzare il gioco. Deve essere implementato dalle sottoclassi.
     * @throws Exception
     */
    public abstract void init() throws Exception;

    /**
     * Metodo astratto per gestire la prossima mossa del giocatore. Deve essere implementato dalle sottoclassi.
     * @param p Oggetto ParserOutput che contiene il risultato del parsing del comando del giocatore
     * @param out PrintStream per l'output del gioco
     */
    public abstract void nextMove(ParserOutput p, PrintStream out);
    
    /**
     * Metodo astratto per ottenere il messaggio di benvenuto del gioco. Deve essere implementato dalle sottoclassi.
     * @return Messaggio di benvenuto
     */
    public abstract String getWelcomeMsg();

    /**
     * Imposta l'inventario del giocatore.
     * @param inventory Lista degli oggetti da impostare nell'inventario
     */
    public void setInventory(List<AdvObject> inventory) {
        this.inventory.clear();
        this.inventory.addAll(inventory);
    }

    /**
     * Restituisce lo username del giocatore.
     * @return Username del giocatore
     */
    public String getUsername() {
        return username;
    }

    /**
     * Imposta lo username del giocatore.
     * @param username Username da impostare
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
}
