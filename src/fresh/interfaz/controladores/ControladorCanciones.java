package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Cancion;
import fresh.datos.tipos.EstadoCancion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.JCustomButton;
import fresh.interfaz.vistas.VistaCanciones;
import fresh.interfaz.vistas.VistaMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 * Este controlador carga las canciones subidas por el usuario, permitiéndole
 * reproducirlas, eliminarlas y actualizarlas en caso de ser necesario, así como
 * subir nuevas.
 */
public class ControladorCanciones {

    public ControladorCanciones(Sistema sistema, VistaCanciones vistaCanciones, VistaMenu vistaMenu) {
        cargarCanciones(sistema, vistaCanciones, vistaMenu);

        vistaCanciones.botonSubirCancion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCancion = vistaCanciones.entradaNombreCancion.getText();
                if (nombreCancion.length() == 0) return;

                if (vistaCanciones.selectorArchivo.showOpenDialog(vistaCanciones) == JFileChooser.APPROVE_OPTION) {
                    sistema.subirCancion(nombreCancion, vistaCanciones.selectorArchivo.getSelectedFile().getAbsolutePath());
                    cargarCanciones(sistema, vistaCanciones, vistaMenu);
                    vistaCanciones.scrollPanel.repaint();
                }
            }
        });
    }

    private void cargarCanciones(Sistema sistema, VistaCanciones vistaCanciones, VistaMenu vistaMenu) {
        int numCanciones = sistema.getUsuarioActual().getCanciones().size();
        
        if (numCanciones == 0) {
            vistaCanciones.scrollFrame.setVisible(false);
            vistaCanciones.textoSinCanciones.setVisible(true);
            return;
        }

        vistaCanciones.textoSinCanciones.setVisible(false);
        vistaCanciones.scrollFrame.setVisible(true);
        
        vistaCanciones.scrollPanel.setPreferredSize(new Dimension(0, 15+numCanciones*100));
        vistaCanciones.scrollPanel.removeAll();
        vistaCanciones.scrollFrame.revalidate();
        vistaCanciones.scrollFrame.repaint();

        int i = 0;
        for (Cancion c : sistema.getUsuarioActual().getCanciones()) {
            JLabel textoDuracion = new JLabel(String.valueOf(c.getDuracion()/60) + ":" + String.format("%02d", c.getDuracion()%60));
            textoDuracion.setBounds(185, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaCanciones.scrollPanel.add(textoDuracion);

            JLabel textoNombreCancion = new JLabel(c.getNombre());
            textoNombreCancion.setBounds(295, 15+100*i, 450, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaCanciones.scrollPanel.add(textoNombreCancion);

            JLabel textoEstadoCancion = new JLabel(c.getEstado().aTexto());
            textoEstadoCancion.setBounds(295, 15+100*i+35, 450, 40);
            textoEstadoCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoEstadoCancion.setForeground(Estilo.colorTexto);
            textoEstadoCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaCanciones.scrollPanel.add(textoEstadoCancion);

            JLabel textoNumeroReproducciones = new JLabel(String.valueOf(c.getReproduccionesMensuales()));
            textoNumeroReproducciones.setBounds(750, 15+100*i+5, 120, 40);
            textoNumeroReproducciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNumeroReproducciones.setForeground(Estilo.colorTexto);
            textoNumeroReproducciones.setHorizontalAlignment(JLabel.CENTER);
            vistaCanciones.scrollPanel.add(textoNumeroReproducciones);

            JLabel textoReproducciones = new JLabel("reproducciones");
            textoReproducciones.setBounds(750, 15+100*i+25, 120, 40);
            textoReproducciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 15));
            textoReproducciones.setForeground(Estilo.colorTexto);
            textoReproducciones.setHorizontalAlignment(JLabel.CENTER);
            vistaCanciones.scrollPanel.add(textoReproducciones);

            if (!c.getBloqueado()) {
                JCustomButton botonReproducir = new JCustomButton("▶");
                botonReproducir.setBounds(25, 15+100*i, 75, 75);
                botonReproducir.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
                botonReproducir.setForeground(Estilo.colorTexto);
                botonReproducir.setBackground(new Color(240, 240, 100));
                botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
                botonReproducir.setCornerRadius(80);
                botonReproducir.setHeight(5);       
                botonReproducir.setShadowSize(5);
                botonReproducir.setShadowOpacity(0.4f);            
                vistaCanciones.scrollPanel.add(botonReproducir);

                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vistaMenu.botonReproducir.setVisible(false);
                        vistaMenu.botonParar.setVisible(true);
                        sistema.reproducir(c);
                    }
                });                
            } else if (c.getEstado() == EstadoCancion.RECHAZADA_REVISABLE) {
                JCustomButton botonResubir = new JCustomButton("🔄");
                botonResubir.setBounds(25, 15+100*i, 75, 75);
                botonResubir.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
                botonResubir.setForeground(Estilo.colorTexto);
                botonResubir.setBackground(new Color(10, 200, 90));
                botonResubir.setPressedBackgound(new Color(10, 200, 90).brighter());
                botonResubir.setCornerRadius(80);
                botonResubir.setHeight(5);       
                botonResubir.setShadowSize(5);
                botonResubir.setShadowOpacity(0.4f);            
                vistaCanciones.scrollPanel.add(botonResubir);

                botonResubir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (vistaCanciones.selectorArchivo.showOpenDialog(vistaCanciones) == JFileChooser.APPROVE_OPTION) {
                            sistema.actualizarCancion(c, vistaCanciones.selectorArchivo.getSelectedFile().getAbsolutePath());
                            cargarCanciones(sistema, vistaCanciones, vistaMenu);
                            vistaCanciones.scrollPanel.repaint();
                        }
                    }
                });
            }

            JCustomButton botonEliminar = new JCustomButton("✖");
            botonEliminar.setBounds(110, 15+100*i, 75, 75);
            botonEliminar.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
            botonEliminar.setForeground(Estilo.colorTexto);
            botonEliminar.setBackground(new Color(224, 62, 98));
            botonEliminar.setPressedBackgound(new Color(224, 62, 98).brighter());
            botonEliminar.setCornerRadius(80);
            botonEliminar.setHeight(5);       
            botonEliminar.setShadowSize(5);
            botonEliminar.setShadowOpacity(0.4f);            
            vistaCanciones.scrollPanel.add(botonEliminar);

            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.eliminarCancion(c);
                    cargarCanciones(sistema, vistaCanciones, vistaMenu);
                    vistaCanciones.scrollPanel.repaint();
                }
            });

            i++;
        }
    }
}