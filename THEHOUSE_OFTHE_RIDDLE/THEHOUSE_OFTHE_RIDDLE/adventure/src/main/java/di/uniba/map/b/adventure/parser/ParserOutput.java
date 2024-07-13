package di.uniba.map.b.adventure.parser;

import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;

/**
 * Classe che rappresenta l'output del parser, contenente il comando riconosciuto e gli oggetti coinvolti.
 * 
 */
public class ParserOutput {

    private Command command; // Comando riconosciuto dal parser

    private AdvObject object; // Oggetto presente nella stanza
    
    private AdvObject invObject; // Oggetto presente nell'inventario

    /**
     * Costruttore che inizializza il ParserOutput con un comando e un oggetto.
     *
     * @param command Il comando riconosciuto
     * @param object L'oggetto presente nella stanza
     */
    public ParserOutput(Command command, AdvObject object) {
        this.command = command;
        this.object = object;
    }

    /**
     * Costruttore che inizializza il ParserOutput con un comando, un oggetto nella stanza e un oggetto nell'inventario.
     *
     * @param command Il comando riconosciuto
     * @param object L'oggetto presente nella stanza
     * @param invObejct L'oggetto presente nell'inventario
     */
    public ParserOutput(Command command, AdvObject object, AdvObject invObejct) {
        this.command = command;
        this.object = object;
        this.invObject = invObejct;
    }

    /**
     * Restituisce il comando riconosciuto dal parser.
     *
     * @return Il comando riconosciuto
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Imposta il comando riconosciuto dal parser.
     *
     * @param command Il comando da impostare
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Restituisce l'oggetto presente nella stanza.
     *
     * @return L'oggetto presente nella stanza
     */
    public AdvObject getObject() {
        return object;
    }

    /**
     * Imposta l'oggetto presente nella stanza.
     *
     * @param object L'oggetto da impostare
     */
    public void setObject(AdvObject object) {
        this.object = object;
    }

    /**
     * Restituisce l'oggetto presente nell'inventario.
     *
     * @return L'oggetto presente nell'inventario
     */
    public AdvObject getInvObject() {
        return invObject;
    }

    /**
     * Imposta l'oggetto presente nell'inventario.
     *
     * @param invObject L'oggetto da impostare
     */
    public void setInvObject(AdvObject invObject) {
        this.invObject = invObject;
    }

}
