package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.CasaMapGUI;
import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.GameUtils;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.CommandType;
import java.util.Scanner;

/**
 * Implementazione di un osservatore che gestisce il comando di utilizzo oggetti.
 * (USE - usare oggetti)
 * 
 */

public class UseObserver implements GameObserver {
    
     Scanner scanner1 = new Scanner(System.in);

    private final String hiddenCode = "1234"; // Codice da rivelare nel libro

    /**
     * Aggiorna lo stato del gioco in base all'output del parser.
     *
     * @param description La descrizione attuale del gioco
     * @param parserOutput L'output del parser contenente il comando e l'oggetto
     * @return Un messaggio che descrive il risultato dell'azione
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput) {
        StringBuilder msg = new StringBuilder();
        Scanner scanner = new Scanner(System.in);

         // Controlla se il comando è di tipo USE (usare)
         if (parserOutput.getCommand().getType() == CommandType.USE) {
             boolean interact = false;

             // Cucina
             // Controlla se l'oggetto specificato nel comando è una batteria (ID 1)
             boolean battery = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 1;
             battery = battery || parserOutput.getObject() != null && parserOutput.getObject().getId() == 1;

             // Interagisce con la torcia (ID 3) se la batteria è presente
             if (battery && GameUtils.getObjectFromInventory(description.getInventory(), 3) != null) {
                 GameUtils.getObjectFromInventory(description.getInventory(), 3).setPushable(true);
                 GameUtils.getObjectFromInventory(description.getInventory(), 3).setDescription("Torcia con batterie inserite");
                 msg.append("Hai inserito le batterie nella torcia.!\n");
                 msg.append("Potresti utillizzarla per qualcosa.!\n");
                 interact = true;
             } else if (battery) {
                 msg.append("Non c'e' nessun oggetto nell'inventario che funziona con questo tipo di batterie.\n"
                         + "Prova a trovare la torcia, potrebbe essere nei paraggi...");
                 interact = true;
             }

             // Controlla se l'oggetto specificato nel comando è una torcia (ID 3)
             boolean torch = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 3;
             torch = torch || parserOutput.getObject() != null && parserOutput.getObject().getId() == 3;

             // Verifica se l'utente ha la torcia e le batterie, ma le batterie non sono inserite
             if (torch) {
                 GameUtils.getObjectFromInventory(description.getInventory(), 3);
                 boolean hasBatteries =  GameUtils.getObjectFromInventory(description.getInventory(), 3).isPushable();
                 boolean hasTorch =  GameUtils.getObjectFromInventory(description.getInventory(), 3) != null;

                 if (hasTorch && !hasBatteries) {
                     msg.append("Hai la torcia, ma sembra che le pile non siano inserite.\n");
                     msg.append("Inserisci le pile nella torcia per poterla usare.\n"
                             +  "NOTA BENE: Potresti avere le pile ma non le hai ancora inserite nella torcia\n"
                             + "oppure non hai preso le pile , per avere conferma controlla il tuo inventario.");
                     interact = true;
                 }
                 else if (!hasTorch) {
                     msg.append("Non hai la torcia nell'inventario.\n");
                     msg.append("Forse puoi trovarla qui nelle vicinanze, controlla meglio.\n");
                     interact = true;
                 } else {
                     msg.append("Hai acceso la torcia ma non e' successo nulla.... \n"
                             + "Forse potresti con essa illuminare qualcos'altro...\n"
                             + "Forse un libro.....oppure una Stanza?\n"
                             + "La torcia si spegne....");

                     interact = true;
                 }
             }

             // Controlla se l'oggetto specificato nel comando è un Coltello (ID 2)
             boolean knife = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 2;
             knife = knife || parserOutput.getObject() != null && parserOutput.getObject().getId() == 2;

             if (knife && description.getCurrentRoom().getId() == 1  ) {
                 if (GameUtils.getObjectFromInventory(description.getInventory(), 2) != null ) {
                     System.out.println("Hai forzato il cassetto e tirandolo verso di te hai\n "
                             + "azionato un ordigno.... non hai avuto scampo !\n"
                             + "SEI MORTO");
                     System.out.println("La tua avventura termina qui! Complimenti!");
                     System.exit(0);

                     interact = true;
                 } else {
                     msg.append("Non puoi usare il coltello, non ce l'hai nell'inventario.\n");
                     interact = true;
                 }
             }

             // Salotto
             // Controlla se l'oggetto specificato nel comando è una Candela (ID 4)
             boolean candle = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 4;
             candle = candle || parserOutput.getObject() != null && parserOutput.getObject().getId() == 4;

             // Controlla se l'oggetto specificato nel comando è una Scatola di Fiammiferi (ID 5)
             boolean matches = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 5;
             matches = matches || parserOutput.getObject() != null && parserOutput.getObject().getId() == 5;

             if (matches && description.getCurrentRoom().getId() == 3) {
                 // Controlla se l'utente ha i fiammiferi nell'inventario
                 if (GameUtils.getObjectFromInventory(description.getInventory(), 5) != null) {
                     // Se l'utente ha anche la candela
                     if (GameUtils.getObjectFromInventory(description.getInventory(), 4) != null) {
                         msg.append("Hai acceso la candela con i fiammiferi! La stanza e' ora illuminata.\n");
                         msg.append("Ora il salotto e luminoso.....\n"
                                 + "Noti uno strano pulsante sul muro..Forse premendolo potrai uscire finalmente.");
                         AdvObject button = new AdvObject(11, "Pulsante", "Un bottone e apparso sul muro chissa che fara ?");
                         button.setAlias(new String[]{"pulsante", "bottone"});
                         description.getCurrentRoom().getObjects().add(button);
                         button.setPushable(true);
                         interact = true;              
                     } else {
                         msg.append("Hai i fiammiferi, ma non hai una candela.\n");
                         msg.append("Potresti trovarla qui nelle vicinanze, controlla meglio.\n");
                         interact = true;
                     }
                 } else {
                     msg.append("Non puoi usare i fiammiferi, non ce li hai nell'inventario.\n");
                     interact = true;
                 }
             }

             if (candle) {
                 // Controlla se l'utente ha la candela nell'inventario
                 if (GameUtils.getObjectFromInventory(description.getInventory(), 4) != null) {
                     // Se l'utente ha anche i fiammiferi
                     if (GameUtils.getObjectFromInventory(description.getInventory(), 5) != null) {
                         msg.append("Hai una candela e i fiammiferi. Puoi accendere la candela per illuminare la stanza.\n");
                         interact = true;
                     } else {
                         msg.append("Hai la candela, ma non hai i fiammiferi.\n");
                         msg.append("Potresti trovarli qui nelle vicinanze, controlla meglio.\n");
                         interact = true;
                     }
                 } else {
                     msg.append("Non puoi usare la candela, non ce l'hai nell'inventario.\n");
                     interact = true;
                 }
             }

             // Biblioteca
             // Controlla se l'oggetto specificato nel comando è il Libro degli Enigmi (ID 7)
             boolean book = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 7;
             book = book || parserOutput.getObject() != null && parserOutput.getObject().getId() == 7;

             if (book) {
                 // Controlla se l'utente ha la torcia nell'inventario
                 if (GameUtils.getObjectFromInventory(description.getInventory(), 3) != null &&
                     GameUtils.getObjectFromInventory(description.getInventory(), 3).isPushable()) {
                     msg.append("Effetivamente osservando il libro ti sei accorto che una pagina del libro fosse inccolata con un altra....\n"
                             + "Per questo per vederci meglio prendi la torcia e decidi di illuminare qulla pagina...\n"
                             + "Hai usato la torcia sul libro e scoperto un codice segreto: " + hiddenCode + "\n");
                     interact = true;
                 } else {
                     msg.append("Non puoi leggere il libro poiche' ti sei accorto che le pagine sono tutte mal ridotte tranne una, \n"
                             +  "dalla quale ti sembra di capire che si nasconda qualcosa ma non riesci a vedere per la scarsa luce.\n"
                             + " Potresti utilizzare una torcia. Trovala.....oppure se ne sei in possesso usala.\n");
                     interact = true;
                 }
             }

             // Verifica se l'oggetto specificato nel comando è una Chiave per la soffitta (ID 8)
             boolean keyloft = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 8;
             keyloft = keyloft || parserOutput.getObject() != null && parserOutput.getObject().getId() == 8;

             if (description.getCurrentRoom().getId() == 4 ) {
                 if (keyloft ) {
                     // Controlla se l'utente ha la chiave nell'inventario
                     if (GameUtils.getObjectFromInventory(description.getInventory(), 8) != null) {
                         System.out.println("Hai usato la chiave per aprire la soffitta! Ora puoi accedervi.\n");
                         // Aggiorna lo stato della stanza della soffitta per renderla visibile
                         description.getCurrentRoom().setVisible(true); 
                         if(description.getCurrentRoom().isVisible() == true) {
                             description.getCurrentRoom().setDescription("Entrando, noti a nord una camera da letto sporca e ammuffita \n"
                                     + "a ovest una scala che presumibilmente ti porterebbe fuori dalla stanza, \n"
                                     + "la quale prende luce.\n"
                                     + "Infine, intravedi alle tue spalle una porta ma sembra \n"
                                     + "essere bloccata da delle sbarre di legno.\n");
                             System.out.println(description.getCurrentRoom().getDescription());
                         }
                         interact = true;
                     } else {
                         msg.append("Non puoi accedere alla soffitta senza la chiave.\n");
                         msg.append("Potresti trovarla qui nelle vicinanze, controlla meglio.\n");
                         interact = true;
                     }
                 } 
             }

             // Verifica se l'oggetto specificato nel comando è il Diario degli Enigmi (ID 9)
             boolean Diary = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 9;
             Diary = Diary || parserOutput.getObject() != null && parserOutput.getObject().getId() == 9;

             if (Diary) {
                 if (GameUtils.getObjectFromInventory(description.getInventory(), 9) != null){
                     // Descrizione speciale quando il Diario degli Enigmi viene utilizzato
                     System.out.println("Tutte le pagine di questo libro sono strappate e malridotte,\n ma in una scorgiamo la soluzione per l'uscita.");
                     System.out.println("E' detto che tramite una porta presente sulla soffitta si potrebbe uscire.\n");
                     interact = true;
                 }
             }

             // Verifica se l'oggetto specificato nel comando è una Mappa (ID 10)
             boolean Map = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 10;
             Map = Map || parserOutput.getObject() != null && parserOutput.getObject().getId() == 10;

             if (Map) {
                 if (GameUtils.getObjectFromInventory(description.getInventory(), 10) != null){
                     System.out.println("Hai aperto la mappa della casa:");
                     interact = true;
                     // Qui puoi inserire la logica per mostrare la mappa, ad esempio:
                     CasaMapGUI casaMapUI = new CasaMapGUI();
                     casaMapUI.mostraMappaCasa();
                 }
             }

             // Controlla se l'utente ha la chiave nell'inventario
             if (GameUtils.getObjectFromInventory(description.getInventory(), 14) != null) {
                 System.out.println("Hai usato il piede di porco, togliendo le sbarre di legno e forzando la serratura... Entri.\n");
                 // Aggiorna lo stato della stanza della soffitta per renderla visibile
                 description.getCurrentRoom().setVisible(true);

                 // Domande vere o false
                 String domanda1 = "Java e' un linguaggio di programmazione orientato agli oggetti?"; // Risposta corretta: VERO
                 String domanda2 = "Il valore di 'null' e' un numero?"; // Risposta corretta: FALSO
                 String domanda3 = "Un array in Java può contenere elementi di tipo diverso?"; // Risposta corretta: FALSO

 
                // Lettura delle risposte dell'utente con validazione
                System.out.println(domanda1);
                String rispostaUtente1 = validaRisposta(scanner.nextLine());

                System.out.println(domanda2);
                String rispostaUtente2 = validaRisposta(scanner.nextLine());

                System.out.println(domanda3);
                String rispostaUtente3 = validaRisposta(scanner.nextLine());
                
                
                 // Verifica delle risposte corrette
                 int risposteCorrette = 0;
                 if (rispostaUtente1.equals("v") || rispostaUtente1.equals("vero")) risposteCorrette++;
                 if (rispostaUtente2.equals("f") || rispostaUtente2.equals("falso")) risposteCorrette++;
                 if (rispostaUtente3.equals("f") || rispostaUtente3.equals("falso")) risposteCorrette++;

                 // Controllo se l'utente ha risposto correttamente a almeno due domande
                 if (risposteCorrette >= 2) {
                     System.out.println("Hai risposto correttamente a almeno due domande, hai vinto!\n"
                             + "La tua avventura termina qui... :)\n");
                     System.exit(0);
                     // Aggiungi qui le azioni per il proseguimento del gioco
                 } else {
                     System.out.println("Hai risposto correttamente a meno di due domande , il muro si abbassa e rimani schiacciato...\n"
                             + "La tua avventura termina qui.... :(\n "
                             + "HAI PERSO...\n");
                     interact = false; // Disattiva l'interazione per evitare ulteriori azioni dopo la morte
                     System.exit(0);
                 }
             }

             // Verifica se l'oggetto specificato nel comando è un Tubo (ID 15)
             boolean Pipe = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 15;
             Pipe = Pipe || parserOutput.getObject() != null && parserOutput.getObject().getId() == 15;
             if (Pipe) {
                 description.setCurrentRoom(description.getCurrentRoom().getSouth());
                 interact = true;
             }

             // Messaggio se non ci sono oggetti utilizzabili
             if (!interact) {
                 msg.append("Questo oggetto non e' utillizabile qui.");
             }
         }
         return msg.toString();

    }
    
       // Funzione per validare la risposta dell'utente
        String validaRisposta(String risposta) {
            risposta = risposta.trim().toLowerCase();
            if (risposta.equals("v") || risposta.equals("vero") || risposta.equals("f") || risposta.equals("falso")) {
                return risposta;
            } else {
                System.out.println("Risposta non valida. Inserisci 'vero' o 'falso' oppure 'v' o 'f':");
                return validaRisposta(scanner1.nextLine());
    }
}
    
}



