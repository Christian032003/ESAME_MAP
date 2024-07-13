
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import di.uniba.map.b.adventure.GameObservable;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.type.AdvObjectContainer;


public class THEHOUSEOFTHERIGGER extends GameDescription implements GameObservable {

    private final List<GameObserver> observer = new ArrayList<>();

    private ParserOutput parserOutput;

    private final List<String> messages = new ArrayList<>();

    /**
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        messages.clear();
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi","afferra"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva","schiaccia","inserisci"});
        getCommands().add(push);
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza", "combina"});
        getCommands().add(use);
        
        //Rooms
        Room hall = new Room(0, "Ingresso", "Sei nell'ingresso di una villa abbandonata. "
                + "\n L'aria e' polverosa e senti un leggero odore di muffa."
                + "\n  A nord, puoi vedere una porta che conduce al salotto.");
        
       hall.setLook("Non c'e' nulla di interessante qui .....Prosegui per la prossima stanza.\n");
       
        Room kitchen = new Room(1,"Cucina", "La cucina e' in disordine, con pentole e piatti sparsi ovunque.  "
                + "\n L'odore di cibo avariato e' forte.  "
                + "\n Un tavolo al centro della stanza ha un cassetto che sembra bloccato.");
        
        kitchen.setLook("Puoi vedere vari oggetti sparsi come :"
                + "\n Coltello da cucina affilato "
                + "\n Una Torcia elettrica malfunzionante"
                + "\n Delle Vecchie Pile usate\n ");
        
        Room library = new Room(2,"Biblioteca", "La biblioteca e' piena di libri antichi."
                +"\n   Alla tua sinistra  c'e' una grande scala che porta al piano superiore. "
                + "\n  Un grande scrivania si trova al centro, con un vecchio libro aperto sopra. "
                + "\n  Una finestra e' coperta da una tenda pesante.");
        
        library.setLook("Ci sono degli oggetti pieni di polvere :"
                + "\n Noti un vecchio Libro pieno di enigmi sulla scrivania..."
                + "\n Una Chiave arruginita...."
                + "\n Ed una tenda...evidentemnte si puo aprire..?");
        
        Room livingRoom = new Room(3, "Salotto", "Il salotto e' buio e silenzioso. "
                + "\n  Un grande camino domina la stanza, con alcune poltrone disposte intorno.  "
                + "\n A est, c'e' una porta che conduce alla cucina. A ovest, una porta conduce alla biblioteca.");
        
        livingRoom.setLook("A terra vedi : "
                + "\n Una Candela gia' usata e"
                + "\n Dei Fiammiferi.\n");
        
        Room loft = new Room(4, "Soffitta", " C'e' un porta davanti a te per accedere alla soffita, "
                + "\n ma sembraci voglia una chiave.");
        loft.setVisible(false);//poiche ce bisogno della chiave.
        loft.setLook("Noti che il pavimento e' instabile, vicino a te trovi "
                + "\n Il Diario del propietario della villa."
                + "\n E una mappa che mostra la disposizione delle Stanze....\n");
        
        Room BedRoom = new Room(5,"Camera da letto", "Sei in una camera da letto trasandata e polverosa con un letto matrimoniale, un armadio di legno scuro e un baule robusto. \n"
                + "Il letto e l'armadio sono apribili e potrebbero nascondere segreti. \n"
                + "La stanza, illuminata debolmente da una luce spettrale, emana \n"
                + "un'atmosfera di mistero e inquietudine.");
        BedRoom.setLook("Noti un armadio e un baule chissa che ci ,\n"
                + "sara' mai dentro,,?");
        Room Terrace = new Room(6,"Terrazzo","Sei su un terrazzo logorato dal tempo, con una ringhiera \n"
                + "arrugginita e piastrelle scheggiate coperte di muschio.\n"
                + " Vasi di terracotta rotti e piante avvizzite giacciono qua e la'.\n"
                + "E' in corso un tempesta e vedi poco...Scegli una direzione.\n");
        Terrace.setLook("La nebbia e' fitta vedi molto poco....Scegli una direzione...");
        
        Room Basement = new Room(7,"Seminterrato","La porta davanti a te sembra essere bloccata per la prossima stanza \n"
                +"Chissa cosa si nasconde dietro essa. Noti inoltre che la porta e bloccata da \n"
                +"delle sbarre di legno , prova a trovare qualcosa per aprirla.\n");
        Basement.setVisible(false);//dobbiamo trovare il piede di porco
        Basement.setLook("Sei nel seminterrato della  casa abbandonata, l'aria è umida e pesante, impregnando tutto con un odore di muffa.\n"
                + " Le pareti scrostate rivelano mattoni anneriti, mentre una singola lampadina penzola dal soffitto,\n"
                + " illuminando debolmente angoli pieni di vecchi mobili rotti e scatole dimenticate.\n"
                + " Un silenzio inquietante avvolge l'ambiente, interrotto solo dal gocciolare intermittente dell'acqua.");
        
        Room Garden = new Room(8, "Giardino", "Sei in un giardino abbandonato, con erba alta e \n"
                + "cespugli selvatici che crescono senza controllo.\n"
                + "Dopo essere sceso dalla tubatura , il vento la spezza....\n,"
        );
        Garden.setLook("Davanti a te vedi un cancello chiuso da un luchetto con codice, a 4 cifre...\n"
                + "Ti ricorda qualcosa....\n"
                + "Da ora in poi e' questione di vita o di morte....Devi inserire quel codice\n"
                + "?!:Immeti il comando inserisci.......\n");
       

        
        //map piano terra
        hall.setNorth(livingRoom);
        livingRoom.setSouth(hall);
        livingRoom.setEast(kitchen);
        kitchen.setWest(livingRoom);
        livingRoom.setWest(library);
        library.setEast(livingRoom);
        library.setWest(loft);
        loft.setEast(library);
        //map 1 piano
        loft.setNorth(BedRoom);
        loft.setWest(Terrace);
        Terrace.setEast(loft);
        BedRoom.setSouth(loft);
        loft.setSouth(Basement);
        Basement.setNorth(loft);
        Terrace.setSouth(Garden);
   
       
        getRooms().add(kitchen);
        getRooms().add(livingRoom);
        getRooms().add(hall);
        getRooms().add(library);
        getRooms().add(loft);
        getRooms().add(BedRoom);
        getRooms().add(Terrace);
        getRooms().add(Basement);
        getRooms().add(Garden);
        
        

        //obejcts for kitchen
        AdvObject battery = new AdvObject(1, "Batteria", "Un pacco di batterie, chissa' se sono cariche.");
        battery.setAlias(new String[]{"batterie", "pile", "pila"});
        kitchen.getObjects().add(battery);
        battery.setPickupable(true);
        battery.setUsable(true);
        
        AdvObject knife = new AdvObject(2, "Coltello da cucina", "Un coltello molto affilato.");
        knife.setAlias(new String[]{"coltello"});
        kitchen.getObjects().add(knife);
        knife.setPickupable(true);
        knife.setUsable(true);
    
        AdvObject Torch = new AdvObject(3, "Torcia Elettrica", "Una Torcia malfunzionante.");
        Torch.setAlias(new String[]{"torcia"});
        kitchen.getObjects().add(Torch);
        Torch.setPickupable(true);
        Torch.setUsable(true);
         
          //obejcts for livingRoom
        AdvObject Candle = new AdvObject(4, "Candela", "Una Candela gia' usata.");
        Candle.setAlias(new String[]{"candela","candele"});
        livingRoom.getObjects().add(Candle);
        Candle.setPickupable(true);
        Candle.setUsable(true);
        
        AdvObject Matches = new AdvObject(5, "Fiammiferi", "Un vecchio pacco di Fiammiferi impolverato.");
        Matches.setAlias(new String[]{"fiammiferi", "fiammifero"});
        livingRoom.getObjects().add(Matches);
        Matches.setPickupable(true);
        Matches.setUsable(true);
        
          //obejcts for library
        AdvObject book  = new AdvObject(7, "Libro ","Un vecchio Libro pieno di enigmi.");
        book.setAlias(new String[]{"libro","libri"});
        library.getObjects().add(book);
        book.setPickupable(true);
        book.setUsable(true);
        
        AdvObject keyloft  = new AdvObject(8, "Chiave ","Una chiave arrugginita.");
        keyloft.setAlias(new String[]{"chiave","chiavi"});
        library.getObjects().add(keyloft);
        keyloft.setPickupable(true);
        keyloft.setUsable(true);
        
        AdvObject tent = new AdvObject(12, "Tenda", " La tenda della libreria che copre la finestra.");
        tent.setAlias(new String[]{"tenda"});
        library.getObjects().add(tent);
        tent.setOpenable(true);
       
                        
          //objects for loft
        AdvObject diary  = new AdvObject(9, "Diario ","Il diario del proprietario della villa.");
        diary.setAlias(new String[]{"diario","diario"});
        loft.getObjects().add(diary);
        diary.setPickupable(true);
        diary.setUsable(true);
        AdvObject map  = new AdvObject(10, "Mappa ","Una mappa che mostra la disposizione delle stanze.");
        map.setAlias(new String[]{"mappa","map"});
        loft.getObjects().add(map);
        map.setPickupable(true);
        map.setUsable(true);
        
        //object for BedRoom 
        AdvObject Wardrobe  = new AdvObject(13, "Armadio ","Un armadio vecchio e putrido.");
        Wardrobe.setAlias(new String[]{"armadio","guardaroba"});
        BedRoom.getObjects().add(Wardrobe);
        Wardrobe.setOpenable(true);
        
        AdvObjectContainer Trunk  = new AdvObjectContainer(1, "Baule ","Un baule socchiuso , proprio perche' bloccato da un arnese.\nChissa' che sara'.....?");
        Trunk.setAlias(new String[]{"baule","cassone","forziere"});
        
        
        AdvObject Crowbar = new AdvObject(14, "Piede di Porco ","Il famoso arnese che teneva il baule semichiuso.");
        Crowbar.setAlias(new String[]{"piede di porco","arnese","levachiodi"});
        Trunk.add(Crowbar);
        Crowbar.setUsable(true);
        
        BedRoom.getObjects().add(Trunk);
        Trunk.setOpenable(true);
        
        // Object for Terrace
        AdvObject Pipe = new AdvObject(15, "Tubatura","Una tubatura che potrebbe portatri da qualche parte.");
        Pipe.setAlias(new String[]{"tubatura","canala","tubo"});
        Terrace.getObjects().add(Pipe);
        Pipe.setUsable(true);
        
       
        
        //Observer
        GameObserver moveObserver = new MoveObserver();
        this.attach(moveObserver);
        GameObserver invObserver = new InventoryObserver();
        this.attach(invObserver);
        GameObserver pushObserver = new PushObserver();
        this.attach(pushObserver);
        GameObserver lookatObserver = new LookAtObserver();
        this.attach(lookatObserver);
        GameObserver pickupObserver = new PickUpObserver();
        this.attach(pickupObserver);
        GameObserver openObserver = new OpenObserver();
        this.attach(openObserver);
        GameObserver useObserver = new UseObserver();
        this.attach(useObserver);
        //set starting room
        setCurrentRoom(hall);
    }

            /**
             * Metodo che gestisce il prossimo movimento nel gioco
             *
             * @param p L'output del parser che rappresenta il comando dell'utente
             * @param out Il PrintStream dove verrà stampato l'output del gioco
             */
            @Override
            public void nextMove(ParserOutput p, PrintStream out) {
                // Salva il parserOutput
                parserOutput = p;
                // Pulisce i messaggi precedenti
                messages.clear();

                // Controlla se il comando del parser è nullo
                if (p.getCommand() == null) {
                    out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
                } else {
                    // Ottiene la stanza corrente
                    Room cr = getCurrentRoom();
                    // Notifica gli osservatori
                    notifyObservers();

                    // Determina se il giocatore si è spostato in una nuova stanza
                    boolean move = !cr.equals(getCurrentRoom()) && getCurrentRoom() != null;

                    // Stampa tutti i messaggi raccolti dagli osservatori
                    if (!messages.isEmpty()) {
                        for (String m : messages) {
                            if (m.length() > 0) {
                                out.println(m);
                            }
                        }
                    }

                    // Se il giocatore si è spostato, stampa le informazioni della nuova stanza
                    if (move) {
                        out.println(getCurrentRoom().getName());
                        out.println("===========================================================");
                        out.println(getCurrentRoom().getDescription());
                    }
                }
            }
              /**
               *
               * @param o L'osservatore da attaccare
               */
              @Override
              public void attach(GameObserver o) {
                  // Aggiunge l'osservatore alla lista se non è già presente
                  if (!observer.contains(o)) {
                      observer.add(o);
                  }
              }

              /**
               *
               * @param o L'osservatore da staccare
               */
              @Override
              public void detach(GameObserver o) {
                  // Rimuove l'osservatore dalla lista
                  observer.remove(o);
              }

              /**
               * Notifica tutti gli osservatori
               */
              @Override
              public void notifyObservers() {
                  // Aggiunge i messaggi aggiornati dagli osservatori alla lista di messaggi
                  for (GameObserver o : observer) {
                      messages.add(o.update(this, parserOutput));
                  }
              }

              /**
               *
               * @return Il messaggio di benvenuto
               */
              @Override
              public String getWelcomeMsg() {
                  return " THE HOUSE OF THE RIGGER "
                          + "\n Ti sei addormentato profondamente e, al tuo risveglio, ti trovi in una casa abbandonata."
                          + "\n Le finestre sono coperte di polvere e le porte cigolano a ogni movimento."
                          + "\n Non ricordi come sei arrivato qui, ma sai che devi esplorare questa casa misteriosa per trovare una via d'uscita."
                          + "\n La casa e' piena di stanze oscure, oggetti antichi e segreti nascosti."
                          + "\n Ogni stanza potrebbe contenere indizi preziosi o pericoli inaspettati."
                          + "\n Preparati a risolvere enigmi, affrontare sfide e scoprire la storia di questa casa inquietante"
                          + "\n mentre cerchi disperatamente di tornare alla realta'.\n" ;
              }
}
