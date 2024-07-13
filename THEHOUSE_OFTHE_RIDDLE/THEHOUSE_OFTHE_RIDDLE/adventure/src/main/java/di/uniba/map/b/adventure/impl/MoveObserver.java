/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Classe che implementa l'osservatore per i movimenti nel gioco
 * (MOVE - pemette il movimento nelle varie stanze.)
 */
public class MoveObserver implements GameObserver {

    /**
     * Metodo che viene chiamato per aggiornare l'osservatore
     * 
     * @param description La descrizione del gioco
     * @param parserOutput L'output del parser
     * @return Un messaggio che descrive il risultato del movimento
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput) {
        if (parserOutput.getCommand().getType() == CommandType.NORD) {  // Controlla se il comando è di tipo NORD
            if (description.getCurrentRoom().getNorth() != null) {  // Controlla se esiste una stanza a nord
                description.setCurrentRoom(description.getCurrentRoom().getNorth());  // Imposta la stanza corrente a quella a nord
            }else {
                return "Da quella parte non si può andare!\n";  // Messaggio se non c'è una stanza a nord
            }
        } else if (parserOutput.getCommand().getType() == CommandType.SOUTH) {  // Controlla se il comando è di tipo SOUTH
            if (description.getCurrentRoom().getSouth() != null) {  // Controlla se esiste una stanza a sud
                 if(description.getCurrentRoom().getId()==6){
                description.getCurrentRoom().getObject(15).setUsable(true);
                return "Camminando hai trovato una tubatura chissa dove portera' ?\n";
                }
                description.setCurrentRoom(description.getCurrentRoom().getSouth());  // Imposta la stanza corrente a quella a sud
            } else {
                if(description.getCurrentRoom().getId()==8){
                return "Non puoi tornare indietro la tubatura non c'e piu'....";
                }
                return "Da quella parte non si può andare !\n";  // Messaggio se non c'è una stanza a sud
            }
        } else if (parserOutput.getCommand().getType() == CommandType.EAST) {  // Controlla se il comando è di tipo EAST
            if (description.getCurrentRoom().getEast() != null) {  // Controlla se esiste una stanza a est
                if(description.getCurrentRoom().getId()==6){
                return "Salendo la scala cade e non puoi piu' tornare indietro , sei bloccato.";
                }
                description.setCurrentRoom(description.getCurrentRoom().getEast());  // Imposta la stanza corrente a quella a est
            } else {
                if(description.getCurrentRoom().getId()==8){
                return "Non puoi muoverti sei bloccato.";
                }
                return "Da quella parte non si può andare !\n";  // Messaggio se non c'è una stanza a est
            }
        } else if (parserOutput.getCommand().getType() == CommandType.WEST) {  // Controlla se il comando è di tipo WEST
            if (description.getCurrentRoom().getWest() != null) {  // Controlla se esiste una stanza a ovest
                description.setCurrentRoom(description.getCurrentRoom().getWest());  // Imposta la stanza corrente a quella a ovest
            } else {
                if(description.getCurrentRoom().getId()==8){
                return "Non puoi muoverti sei bloccato.";
                }
                return "Da quella parte non si può andare !\n";  // Messaggio se non c'è una stanza a ovest
            }
        }
        return "";  // Ritorna una stringa vuota se il movimento è avvenuto con successo
    }
}
