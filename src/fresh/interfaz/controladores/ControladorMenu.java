package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaVentana;
import fresh.interfaz.vistas.VistaMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenu {

    public ControladorMenu(Sistema sistema, VistaVentana vistaVentana) {
        VistaMenu vistaMenu = new VistaMenu();
        vistaVentana.add(vistaMenu);

        vistaMenu.botonAnterior.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.reiniciarCancion();
            }
        });

        vistaMenu.botonReproducir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.reanudarCancion();
                vistaMenu.botonReproducir.setVisible(false);
                vistaMenu.botonParar.setVisible(true);
            }
        });

        vistaMenu.botonParar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.pausarCancion();
                vistaMenu.botonParar.setVisible(false);
                vistaMenu.botonReproducir.setVisible(true);
            }
        });

        vistaMenu.botonCerrarSesion.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Status status = sistema.cerrarSesion();
                if (status == Status.OK) {
                    vistaVentana.remove(vistaMenu);
                    @SuppressWarnings("unused")
                    ControladorInicio controladorInicio = new ControladorInicio(sistema, vistaVentana);
                }
            }
        });

        vistaMenu.botonPlaylist.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPlaylist controladorPlaylist = new ControladorPlaylist(sistema, vistaMenu);
            }
        });

        vistaMenu.botonAutores.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorAutores controladorAutores = new ControladorAutores(sistema, vistaMenu);
            }
        });

        vistaMenu.botonMisCanciones.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorCanciones controladorCanciones = new ControladorCanciones(sistema, vistaMenu);
            }
        });

        vistaMenu.botonNotificaciones.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorNotificaciones controladorNotificaciones = new ControladorNotificaciones(sistema, vistaMenu);
            }
        });
        
        vistaVentana.repaint();
    }
}