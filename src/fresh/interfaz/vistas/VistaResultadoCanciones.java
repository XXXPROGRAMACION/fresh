package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;

/**
 * Esta vista aparece cuando se buscan canciones
 */
public class VistaResultadoCanciones extends JPanel {
    private static final long serialVersionUID = 0;

    public JCustomPanel separador;

    public JLabel textoSinResultados;

    public JCustomScrollPane scrollFrame;
    public JPanel scrollPanel;

    public VistaResultadoCanciones(String entrada) {
        setBounds(260, 140, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        separador = new JCustomPanel();
        separador.setBounds(20, 0, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);
        
        textoSinResultados = new JLabel("Sin canciones para \"" + entrada + "\"");
        textoSinResultados.setBounds(35, 10, 765, 40);
        textoSinResultados.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoSinResultados.setForeground(Estilo.colorTexto);
        textoSinResultados.setHorizontalAlignment(JLabel.LEFT);
        textoSinResultados.setVisible(false);
        add(textoSinResultados);

        scrollPanel = new JPanel();
        scrollPanel.setPreferredSize(new Dimension(0, 0));
        scrollPanel.setBackground(Estilo.colorPrimario);
        scrollPanel.setLayout(null);
        
        scrollFrame = new JCustomScrollPane(scrollPanel);
        scrollFrame.setBounds(0, 3, Estilo.anchura-270, 537);
        scrollFrame.setThumbColor(Estilo.colorSecundario.brighter());
        scrollFrame.setTrackColor(Estilo.colorPrimario.darker());
        scrollFrame.setBehindColor(Estilo.colorPrimario);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollFrame);
    }
}