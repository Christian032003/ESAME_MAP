package di.uniba.map.b.adventure;

import javax.swing.*;
import java.awt.*;

public class CasaMapGUI extends JFrame {

    public CasaMapGUI() {
        super("Mappa della Casa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creazione del pannello principale con sfondo bianco
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setLayout(new BorderLayout());

        // Primo piano
        JPanel primoPianoPanel = new JPanel(new BorderLayout());
        primoPianoPanel.setBorder(BorderFactory.createTitledBorder("Primo Piano"));
        primoPianoPanel.setOpaque(false); // Trasparenza per vedere lo sfondo

        // Stanze del primo piano
        JLabel ingressoSudLabel = createRoomLabel("Ingresso");
        JLabel salottoNordLabel = createRoomLabel("Salotto");
        JLabel cucinaEstLabel = createRoomLabel("Cucina");
        JLabel bibliotecaOvestLabel = createRoomLabel("Biblioteca");

        primoPianoPanel.add(ingressoSudLabel, BorderLayout.SOUTH);
        primoPianoPanel.add(salottoNordLabel, BorderLayout.CENTER);
        primoPianoPanel.add(cucinaEstLabel, BorderLayout.EAST);
        primoPianoPanel.add(bibliotecaOvestLabel, BorderLayout.WEST);

        contentPane.add(primoPianoPanel, BorderLayout.NORTH);

        // Secondo piano
        JPanel secondoPianoPanel = new JPanel(new BorderLayout());
        secondoPianoPanel.setBorder(BorderFactory.createTitledBorder("Secondo Piano"));
        secondoPianoPanel.setOpaque(false); // Trasparenza per vedere lo sfondo

        // Stanze del secondo piano
        JLabel soffittaLabel = createRoomLabel("Soffitta");
        JLabel cameraDaLettoLabel = createRoomLabel("Camera da Letto");
        JLabel portaLabel = createRoomLabel("Porta");
        JLabel terrazzoLabel = createRoomLabel("Terrazzo");

        secondoPianoPanel.add(soffittaLabel, BorderLayout.CENTER);
        secondoPianoPanel.add(cameraDaLettoLabel, BorderLayout.NORTH);
        secondoPianoPanel.add(portaLabel, BorderLayout.SOUTH);
        secondoPianoPanel.add(terrazzoLabel, BorderLayout.WEST);

        contentPane.add(secondoPianoPanel, BorderLayout.CENTER);

        // Aggiunta del pannello contenuto alla finestra
        setContentPane(contentPane);
        setSize(700, 550); // Dimensioni della finestra
        setLocationRelativeTo(null); // Posiziona la finestra al centro dello schermo
    }

    // Metodo per creare una JLabel per rappresentare una stanza
    private JLabel createRoomLabel(String roomName) {
        JLabel label = new JLabel(roomName);
        label.setOpaque(true);
        label.setBackground(Color.gray); // Sfondo grigio
        label.setForeground(Color.black); // Testo nero
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setPreferredSize(new Dimension(150, 50)); // Dimensioni della stanza
        return label;
    }

    // Metodo per mostrare la finestra
    public void mostraMappaCasa() {
        SwingUtilities.invokeLater(() -> {
            CasaMapGUI casaMapUI = new CasaMapGUI();
            casaMapUI.setVisible(true);
        });
    }

}
