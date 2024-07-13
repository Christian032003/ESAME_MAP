package di.uniba.map.b.adventure.type;

/**
 * Enumerazione per i tipi di comando nel gioco di avventura.
 */
public enum CommandType {

    /**
     * Comando per terminare il gioco.
     */
    END,

    /**
     * Comando per visualizzare l'inventario.
     */
    INVENTORY,

    /**
     * Comando per muoversi a nord.
     */
    NORD,

    /**
     * Comando per muoversi a sud.
     */
    SOUTH,

    /**
     * Comando per muoversi a est.
     */
    EAST,

    /**
     * Comando per muoversi a ovest.
     */
    WEST,

    /**
     * Comando per aprire un oggetto.
     */
    OPEN,

    /**
     * Comando per chiudere un oggetto.
     */
    CLOSE,

    /**
     * Comando per spingere un oggetto.
     */
    PUSH,

    /**
     * Comando per tirare un oggetto.
     */
    PULL,

    /**
     * Comando per camminare verso un oggetto o una direzione.
     */
    WALK_TO,

    /**
     * Comando per raccogliere un oggetto.
     */
    PICK_UP,

    /**
     * Comando per parlare con un personaggio.
     */
    TALK_TO,

    /**
     * Comando per dare un oggetto a un personaggio.
     */
    GIVE,

    /**
     * Comando per usare un oggetto.
     */
    USE,

    /**
     * Comando per guardare un oggetto o un'area.
     */
    LOOK_AT,

    /**
     * Comando per accendere un oggetto.
     */
    TURN_ON,

    /**
     * Comando per spegnere un oggetto.
     */
    TURN_OFF
}
