package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Album;
import fresh.datos.tipos.Cancion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.JCustomButton;
import fresh.interfaz.vistas.VistaAlbumes;
import fresh.interfaz.vistas.VistaCrearAlbum;
import fresh.interfaz.vistas.VistaMenu;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.GregorianCalendar;

public class ControladorAlbumes {

    public ControladorAlbumes(Sistema sistema, VistaAlbumes vistaAlbumes, VistaMenu vistaMenu) {
        cargarAlbumes(sistema, vistaAlbumes, vistaMenu);

        vistaAlbumes.botonCrearAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarCanciones(sistema, vistaAlbumes, vistaMenu);
            }
        });
    }

    private void cargarAlbumes(Sistema sistema, VistaAlbumes vistaAlbumes, VistaMenu vistaMenu) {
        int numAlbumes = sistema.getUsuarioActual().getAlbumes().size();
        vistaAlbumes.scrollPanel.setPreferredSize(new Dimension(0, 15+numAlbumes*100));
        vistaAlbumes.scrollPanel.removeAll();
        vistaAlbumes.scrollFrame.revalidate();
        vistaAlbumes.scrollFrame.repaint();

        int i = 0;
        for (Album a : sistema.getUsuarioActual().getAlbumes()) {
            JLabel textoDuracion = new JLabel(String.valueOf(a.getDuracion()/60) + ":" + String.format("%02d", a.getDuracion()%60));
            textoDuracion.setBounds(100, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaAlbumes.scrollPanel.add(textoDuracion);

            JLabel textoNombreAlbum = new JLabel(a.getNombre());
            textoNombreAlbum.setBounds(210, 15+100*i, 590, 40);
            textoNombreAlbum.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreAlbum.setForeground(Estilo.colorTexto);
            textoNombreAlbum.setHorizontalAlignment(JLabel.LEFT);
            vistaAlbumes.scrollPanel.add(textoNombreAlbum);

            JLabel textoAnoAlbum = new JLabel("" + a.getAno());
            textoAnoAlbum.setBounds(210, 15+100*i+35, 590, 40);
            textoAnoAlbum.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoAnoAlbum.setForeground(Estilo.colorTexto);
            textoAnoAlbum.setHorizontalAlignment(JLabel.LEFT);
            vistaAlbumes.scrollPanel.add(textoAnoAlbum);

            if (a.getBloqueado()) {
                i++;
                continue;
            }

            JCustomButton botonReproducir = new JCustomButton("▶");
            botonReproducir.setBounds(25, 15+100*i, 75, 75);
            botonReproducir.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
            botonReproducir.setForeground(Estilo.colorTexto);
            botonReproducir.setBackground(new Color(240, 240, 100));
            botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonReproducir.setCornerRadius(80);
            botonReproducir.setHeight(5);       
            botonReproducir.setShadowSize(5);
            botonReproducir.setShadowOpacity(0.4f);            
            vistaAlbumes.scrollPanel.add(botonReproducir);

            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vistaMenu.botonReproducir.setVisible(false);
                    vistaMenu.botonParar.setVisible(true);
                    sistema.reproducir(a);
                }
            });

            i++;
        }
    }

    private void cargarCanciones(Sistema sistema, VistaAlbumes vistaAlbumes, VistaMenu vistaMenu) {
        List<Cancion> cancionesAnadidas = new LinkedList<>();
        String nombreAlbum = vistaAlbumes.entradaNombreAlbum.getText();
        String stringAno = vistaAlbumes.entradaAno.getText();

        if (nombreAlbum.length() == 0 || stringAno.length() == 0) return;

        try {
            Integer ano = Integer.parseInt(stringAno);
            if (ano < 1000 || ano > new GregorianCalendar().get(GregorianCalendar.YEAR)) return;

            List<Cancion> canciones = sistema.getUsuarioActual().getCanciones();
            VistaCrearAlbum vistaCrearAlbum = new VistaCrearAlbum(nombreAlbum);
            vistaCrearAlbum.scrollPanel.setPreferredSize(new Dimension(0, 35+canciones.size()*100));

            int i = 0;
            for (Cancion c : canciones) {
                JLabel textoDuracion = new JLabel(String.valueOf(c.getDuracion()/60) + ":" + String.format("%02d", c.getDuracion()%60));
                textoDuracion.setBounds(200, 15+100*i+20, 80, 40);
                textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
                textoDuracion.setForeground(Estilo.colorTexto);
                textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
                vistaCrearAlbum.scrollPanel.add(textoDuracion);

                JLabel textoNombreCancion = new JLabel(c.getNombre());
                textoNombreCancion.setBounds(310, 15+100*i, 490, 40);
                textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
                textoNombreCancion.setForeground(Estilo.colorTexto);
                textoNombreCancion.setHorizontalAlignment(JLabel.LEFT);
                vistaCrearAlbum.scrollPanel.add(textoNombreCancion);
    
                JLabel textoEstadoCancion = new JLabel(c.getEstado().aTexto());
                textoEstadoCancion.setBounds(310, 15+100*i+35, 490, 40);
                textoEstadoCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
                textoEstadoCancion.setForeground(Estilo.colorTexto);
                textoEstadoCancion.setHorizontalAlignment(JLabel.LEFT);
                vistaCrearAlbum.scrollPanel.add(textoEstadoCancion);

                JCustomButton botonAnadir = new JCustomButton("Seleccionar");
                botonAnadir.setBounds(25, 15+100*i, 175, 75);
                botonAnadir.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
                botonAnadir.setForeground(Estilo.colorTexto);
                botonAnadir.setBackground(new Color(10, 200, 90));
                botonAnadir.setPressedBackgound(new Color(10, 200, 90).brighter());
                botonAnadir.setCornerRadius(80);
                botonAnadir.setHeight(5);       
                botonAnadir.setShadowSize(5);
                botonAnadir.setShadowOpacity(0.4f);
                vistaCrearAlbum.scrollPanel.add(botonAnadir);

                JCustomButton botonQuitar = new JCustomButton("Quitar");
                botonQuitar.setBounds(25, 15+100*i, 175, 75);
                botonQuitar.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
                botonQuitar.setForeground(Estilo.colorTexto);
                botonQuitar.setBackground(new Color(245, 100, 100));
                botonQuitar.setPressedBackgound(new Color(245, 100, 100).brighter());
                botonQuitar.setCornerRadius(80);
                botonQuitar.setHeight(5);       
                botonQuitar.setShadowSize(5);
                botonQuitar.setShadowOpacity(0.4f);
                botonQuitar.setVisible(false);
                vistaCrearAlbum.scrollPanel.add(botonQuitar);

                botonAnadir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancionesAnadidas.add(c);
                        botonAnadir.setVisible(false);
                        botonQuitar.setVisible(true);
                    }
                });

                botonQuitar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancionesAnadidas.remove(c);
                        botonQuitar.setVisible(false);
                        botonAnadir.setVisible(true);
                    }
                });

                i++;
            }

            vistaCrearAlbum.botonCrear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cancionesAnadidas.size() == 0) return;

                    sistema.crearAlbum(nombreAlbum, ano, cancionesAnadidas);

                    if (vistaMenu.panelActual != null) {
                        vistaMenu.remove(vistaMenu.panelActual);
                    }
                    
                    VistaAlbumes vistaAlbumes = new VistaAlbumes();
                    vistaMenu.panelActual = vistaAlbumes;
                    vistaMenu.add(vistaAlbumes);
    
                    @SuppressWarnings("unused")
                    ControladorAlbumes controladorAlbumes = new ControladorAlbumes(sistema, vistaAlbumes, vistaMenu);
    
                    vistaAlbumes.setVisible(true);
    
                    vistaMenu.repaint();
                }
            });

            vistaCrearAlbum.botonCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (vistaMenu.panelActual != null) {
                        vistaMenu.remove(vistaMenu.panelActual);
                    }
                    
                    VistaAlbumes vistaAlbumes = new VistaAlbumes();
                    vistaMenu.panelActual = vistaAlbumes;
                    vistaMenu.add(vistaAlbumes);
    
                    @SuppressWarnings("unused")
                    ControladorAlbumes controladorAlbumes = new ControladorAlbumes(sistema, vistaAlbumes, vistaMenu);
    
                    vistaAlbumes.setVisible(true);
    
                    vistaMenu.repaint();
                }
            });

            vistaAlbumes.scrollFrame.setVisible(false);
            vistaAlbumes.entradaNombreAlbum.setVisible(false);
            vistaAlbumes.entradaAno.setVisible(false);
            vistaAlbumes.botonCrearAlbum.setVisible(false);
            vistaAlbumes.separador.setVisible(false);
            vistaCrearAlbum.setVisible(true);
            vistaAlbumes.add(vistaCrearAlbum);
            vistaAlbumes.repaint();
        } catch (NumberFormatException exception) {
            return;
        }
    }
}