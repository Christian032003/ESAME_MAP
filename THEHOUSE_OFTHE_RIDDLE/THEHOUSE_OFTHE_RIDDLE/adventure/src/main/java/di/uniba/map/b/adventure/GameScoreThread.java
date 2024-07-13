package di.uniba.map.b.adventure;

import java.util.List;
import di.uniba.map.b.adventure.type.AdvObject;

/**
 * Thread per calcolare e comunicare il punteggio del gioco.
 */
public class GameScoreThread extends Thread {
    private final List<AdvObject> inventory;

    public GameScoreThread(List<AdvObject> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void run() {
        // Calcola il punteggio come il numero di oggetti nell'inventario
        int score = inventory.size();
        // Comunica il punteggio
        System.out.println("Il gioco è terminato. Il tuo punteggio e': " + score);
    }
}
