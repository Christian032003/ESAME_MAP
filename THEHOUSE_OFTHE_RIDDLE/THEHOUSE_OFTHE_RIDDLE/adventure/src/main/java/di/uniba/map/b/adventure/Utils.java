package di.uniba.map.b.adventure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe di utilità per il gioco di avventura.
 * Fornisce metodi statici per operazioni comuni.
 */
public class Utils {

    /**
     * Carica un file di testo e restituisce un set di stringhe contenente ogni riga del file.
     * @param file File da leggere
     * @return Set di stringhe contenente le righe del file
     * @throws IOException
     */
    public static Set<String> loadFileListInSet(File file) throws IOException {
        // Crea un nuovo HashSet per contenere le righe del file
        Set<String> set = new HashSet<>();
        // Crea un BufferedReader per leggere il file
        BufferedReader reader = new BufferedReader(new FileReader(file));
        // Legge il file riga per riga finché ci sono righe disponibili
        while (reader.ready()) {
            // Aggiunge la riga letta (convertita in minuscolo e rimossa degli spazi) al set
            set.add(reader.readLine().trim().toLowerCase());
        }
        // Chiude il BufferedReader
        reader.close();
        // Restituisce il set di stringhe
        return set;
    }

    /**
     * Suddivide una stringa in token, rimuovendo le stopwords.
     * @param string Stringa da suddividere
     * @param stopwords Set di stopwords da rimuovere
     * @return Lista di token risultanti
     */
    public static List<String> parseString(String string, Set<String> stopwords) {
        // Crea una nuova lista per contenere i token
        List<String> tokens = new ArrayList<>();
        // Suddivide la stringa in token usando lo spazio come delimitatore e converte in minuscolo
        String[] split = string.toLowerCase().split("\\s+");
        // Itera attraverso ogni token
        for (String t : split) {
            // Se il token non è una stopword, lo aggiunge alla lista dei token
            if (!stopwords.contains(t)) {
                tokens.add(t);
            }
        }
        // Restituisce la lista dei token
        return tokens;
    }

}
