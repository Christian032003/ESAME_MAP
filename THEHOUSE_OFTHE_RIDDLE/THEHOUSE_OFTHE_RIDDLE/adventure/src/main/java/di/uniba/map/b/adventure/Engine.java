package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.impl.THEHOUSEOFTHERIGGER;
import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Engine {

    // Riferimento al gioco in esecuzione
    private GameDescription game;

    // Parser per analizzare i comandi dell'utente
    private Parser parser;
    
    // Contatore per i tentativi di inserimento del codice
    private int tentativi;

    /**
     * Costruttore dell'Engine
     *
     * @param game Il gioco da eseguire
     */
    public Engine(GameDescription game) {
        this.game = game;
        initializeGame();
    }

    private void initializeGame() {
        this.tentativi = 0;
        try {
            // Inizializza il gioco
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            // Carica le stopwords da un file e inizializza il parser
            Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Metodo principale che esegue il loop del gioco
     */
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        
        // Mostra il menu principale
        showMainMenu();
        
        // Loop per leggere e processare i comandi dell'utente
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            
            switch (command.toLowerCase()) {
                case "gioca":
                    startGame();
                    break;
                case "help":
                    showHelp();
                    break;
                case "crediti":
                    showCredits();
                    break;
                case "esci":
                    System.out.println("Grazie per aver giocato! Arrivederci!");
                    return;
                default:
                    System.out.println("Comando non valido. Digita 'help' per vedere i comandi disponibili.");
            }
            
            // Prompt per il prossimo comando
            System.out.println();
            showMainMenu();
        }
    }
    
   /**
     * Mostra il menu principale del gioco
     */
    private void showMainMenu() {
        System.out.println("================================");
        System.out.println("*           MENU PRINCIPALE    *");
        System.out.println("================================");
        System.out.println("1. Gioca");
        System.out.println("2. Help");
        System.out.println("3. Crediti");
        System.out.println("4. Esci");
        System.out.println("================================");
        System.out.print("?> ");
    }
    
    /**
     * Avvia il gioco
     */
    private void startGame() {
        // Crea una nuova istanza del gioco e reimposta lo stato
        this.game = new THEHOUSEOFTHERIGGER();
        initializeGame();
        
        // Stampa l'intestazione del gioco
        System.out.println("================================");
        System.out.println("* Avventura v. 0.1 - 2023-2024 *");
        System.out.println("*         developed by         *");
        System.out.println("*       Christian Termine      *");
        System.out.println("*       Antonella Maldera      *");
        System.out.println("*       Sara Miticocchio       *");
        System.out.println("================================");
        System.out.println();
        
        // Esegui il loop del gioco
        executeGameLoop();
    }
    
    /**
     * Esegue il loop principale del gioco
     */
    private void executeGameLoop() {
        Scanner scanner = new Scanner(System.in);
        
        // Stampa il messaggio di benvenuto
        System.out.println(game.getWelcomeMsg());
        System.out.println();
        
        // Stampa la stanza corrente e la sua descrizione iniziale
        System.out.println("================================================");
        System.out.println("Ti trovi qui: " + game.getCurrentRoom().getName());
        System.out.println();
        System.out.println(game.getCurrentRoom().getDescription());
        System.out.println();
        
        // Loop per leggere e processare i comandi dell'utente
        while (true) {
            System.out.print("?> ");
            String input = scanner.nextLine();

            // Controllo se il comando è "help"
            if (input.equalsIgnoreCase("help")) {
                showHelp();
                continue;
            }
            
            // Parsa il comando inserito dall'utente
            ParserOutput p = parser.parse(input, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            
            if (p == null || p.getCommand() == null) {
                // Se il comando non è riconosciuto
                System.out.println("Non capisco quello che mi vuoi dire.");
            } else if (p.getCommand().getType() == CommandType.END) {
                // Se il comando è di tipo END, termina il gioco
                System.out.println("Sei un codardo, addio!");
                break;
            } else {
                // Controllo specifico per il lucchetto nella stanza con ID 8
                if (game.getCurrentRoom().getId() == 8 && isInsertCommand(input)) {
                    if (handleCodeInput(scanner)) {
                        break;
                    }
                } else {
                    // Esegue il comando normale nel gioco
                    game.nextMove(p, System.out);
                    
                    // Controllo se il comando era "guarda" nella stanza con ID 8
                    if (game.getCurrentRoom().getId() == 8 && p.getCommand().getName().equalsIgnoreCase("guarda")) {
                        // Mostra la descrizione del lucchetto
                        System.out.println(game.getCurrentRoom().getDescription());
                    }
                }
            }
        }

        // Calcola e mostra il punteggio
        int score = game.getInventory().size();
        System.out.println("Il tuo punteggio è: " + score);

        // Ritorna al menu principale
        System.out.println("Tornando al menu principale...");
    }
    
    /**
     * Gestisce l'inserimento del codice nella stanza con ID 8
     *
     * @param scanner L'oggetto Scanner per leggere l'input dell'utente
     * @return true se il gioco deve terminare, false altrimenti
     */
    private boolean handleCodeInput(Scanner scanner) {
        while (tentativi < 3) {
            System.out.print("Inserisci il codice: ");
            String codice = scanner.nextLine().trim(); // Rimuove spazi superflui dall'input
            if (codice.equals("1234")) {
                System.out.println("Codice corretto! Hai vinto!");
                return true;
            } else {
                tentativi++;
                if (tentativi >= 3) {
                    System.out.println("Codice errato. Sei stato trafitto da frecce velenose. Game over.");
                    return true;
                } else {
                    System.out.println("Codice errato. Hai " + (3 - tentativi) + " tentativi rimanenti.");
                }
            }
        }
        return false;
    }

    /**
     * Determina se il comando inserito è "inserisci", ignorando la distinzione
     * tra maiuscole e minuscole.
     *
     * @param command Il comando inserito dall'utente
     * @return true se il comando è "inserisci", false altrimenti
     */
    private boolean isInsertCommand(String command) {
        return command.trim().equalsIgnoreCase("inserisci");
    }

    /**
     * Mostra le istruzioni e i comandi disponibili
     */
    private void showHelp() {
        System.out.println("===========================================================================");
        System.out.println("*                             ISTRUZIONI                                  *");
        System.out.println("============================================================================");
        System.out.println("I comandi disponibili sono:");
        System.out.println("- 'aiuto' o 'help': Mostra questo messaggio di aiuto");
        System.out.println("- 'vai <direzione>': Muoviti in una direzione (es. nord, sud, est, ovest)");
        System.out.println("- 'guarda': Mostra la descrizione della stanza attuale");
        System.out.println("- 'prendi <oggetto>': Raccogli un oggetto nella stanza");
        System.out.println("- 'usa <oggetto>': Usa un oggetto nell'inventario o nella stanza");
        System.out.println("- 'apri <oggetto>': Per aprire un ogetto situato in una stanza");
        System.out.println("- 'premi <oggetto>':Per premere un oggetto situato in una stanza ");
        System.out.println("- 'inventario o inv ': Mostra l'inventario dell'utente");
        System.out.println("- 'fine oppure esci(muori)': Termina il gioco");
        System.out.println("============================================================================");
        System.out.println();
    }

    /**
     * Mostra i crediti del gioco
     */
    private void showCredits() {
        System.out.println("============================================================================");
        System.out.println("                                 CREDITI           ");
        System.out.println("============================================================================");
        System.out.println("                              Sviluppato da:");
        System.out.println("                          - Christian Termine");
        System.out.println("                          - Antonella Maldera");
        System.out.println("                          - Sara Miticocchio");
        System.out.println("============================================================================");
        System.out.println();
    }

    /**
     * Metodo main per eseguire il gioco
     *
     * @param args Argomenti della riga di comando (non usati)
     */
    public static void main(String[] args) {
        // Crea un'istanza del gioco e avvia l'engine
        Engine engine = new Engine(new THEHOUSEOFTHERIGGER());
        engine.execute();
    }
}
