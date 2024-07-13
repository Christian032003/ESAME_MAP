package di.uniba.map.b.adventure.parser;

import di.uniba.map.b.adventure.Utils;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import java.util.List;
import java.util.Set;

/**
 * Classe che implementa il parser del gioco.
 * Il parser riconosce comandi semplici del tipo <azione> <oggetto> <oggetto>.
 * Articoli e preposizioni vengono rimossi durante l'analisi.
 * 
 */
public class Parser {

    private final Set<String> stopwords; // Insieme di parole da ignorare nel parsing (articoli, preposizioni, ecc.)

    /**
     * Costruttore della classe Parser.
     *
     * @param stopwords Insieme delle parole da ignorare nel parsing
     */
    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    /**
     * Metodo che verifica se il token è un comando valido.
     *
     * @param token Token da verificare
     * @param commands Lista dei comandi disponibili
     * @return L'indice del comando nella lista, o -1 se non trovato
     */
    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metodo che verifica se il token è un oggetto valido.
     *
     * @param token Token da verificare
     * @param objects Lista degli oggetti disponibili
     * @return L'indice dell'oggetto nella lista, o -1 se non trovato
     */
    private int checkForObject(String token, List<AdvObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getName().equals(token) || objects.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metodo principale del parser che analizza il comando inserito dal giocatore.
     *
     * @param command Comando inserito dal giocatore
     * @param commands Lista dei comandi disponibili
     * @param objects Lista degli oggetti presenti nella stanza
     * @param inventory Lista degli oggetti presenti nell'inventario
     * @return Un oggetto ParserOutput che contiene il comando riconosciuto e gli oggetti coinvolti
     */
    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {
        List<String> tokens = Utils.parseString(command, stopwords); // Tokenizza il comando rimuovendo le stopwords
        if (!tokens.isEmpty()) {
            int ic = checkForCommand(tokens.get(0), commands); // Controlla se il primo token è un comando valido
            if (ic > -1) {
                if (tokens.size() > 1) {
                    int io = checkForObject(tokens.get(1), objects); // Controlla se il secondo token è un oggetto valido
                    int ioinv = -1;
                    if (io < 0 && tokens.size() > 2) {
                        io = checkForObject(tokens.get(2), objects); // Controlla se il terzo token è un oggetto valido (se il secondo non lo era)
                    }
                    if (io < 0) {
                        ioinv = checkForObject(tokens.get(1), inventory); // Controlla se il secondo token è un oggetto dell'inventario
                        if (ioinv < 0 && tokens.size() > 2) {
                            ioinv = checkForObject(tokens.get(2), inventory); // Controlla se il terzo token è un oggetto dell'inventario (se il secondo non lo era)
                        }
                    }
                    if (io > -1 && ioinv > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), inventory.get(ioinv));
                    } else if (io > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), null);
                    } else if (ioinv > -1) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
                    } else {
                        return new ParserOutput(commands.get(ic), null, null);
                    }
                } else {
                    return new ParserOutput(commands.get(ic), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }

}
