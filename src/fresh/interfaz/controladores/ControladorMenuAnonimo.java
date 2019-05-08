package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenuAnonimo {
	public ControladorMenuAnonimo (Sistema sistema, VistaVentana vistaVentana) {
		VistaMenuAnonimo vistaMenuAnonimo = new VistaMenuAnonimo();
        vistaVentana.add(vistaMenuAnonimo);
        
        vistaMenuAnonimo.botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = vistaMenuAnonimo.entradaBusqueda.getText();
                if (entrada.length() == 0) return;

                if (vistaMenuAnonimo.panelActual != null) {
                    vistaMenuAnonimo.remove(vistaMenuAnonimo.panelActual);
                    vistaMenuAnonimo.panelActual = null;
                }

                if (vistaMenuAnonimo.seleccionModoBusqueda.getSelectedIndex() == 0) {
                    //Canciones
                    VistaResultadoCanciones vistaResultadoCanciones = new VistaResultadoCanciones(entrada);
                    vistaMenuAnonimo.panelActual = vistaResultadoCanciones;
                    vistaMenuAnonimo.add(vistaResultadoCanciones);

                    @SuppressWarnings("unused")
                    ControladorResultadoCancionesNoPlaylist controladorResultadoCancionesNoPlaylist = new ControladorResultadoCancionesNoPlaylist(sistema, vistaResultadoCanciones, vistaMenuAnonimo, entrada);
                
                    vistaResultadoCanciones.setVisible(true);
                } else if (vistaMenuAnonimo.seleccionModoBusqueda.getSelectedIndex() == 1) {
                    //Álbumes
                    VistaResultadoAlbumes vistaResultadoAlbumes = new VistaResultadoAlbumes(entrada);
                    vistaMenuAnonimo.panelActual = vistaResultadoAlbumes;
                    vistaMenuAnonimo.add(vistaResultadoAlbumes);

                    @SuppressWarnings("unused")
                    ControladorResultadoAlbumes controladorResultadoAlbumes = new ControladorResultadoAlbumes(sistema, vistaResultadoAlbumes, vistaMenuAnonimo, entrada);
                
                    vistaResultadoAlbumes.setVisible(true);
                } else {
                    //Autores
                }

                vistaMenuAnonimo.repaint();
            }
        });
        
        vistaMenuAnonimo.botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Status status = sistema.cerrarSesion();
                if (status == Status.OK) {
                    vistaVentana.remove(vistaMenuAnonimo);
                    @SuppressWarnings("unused")
                    ControladorRegistro controladorRegistro= new ControladorRegistro(sistema, vistaVentana);
                }
            }
        });
        
        vistaMenuAnonimo.botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Status status = sistema.cerrarSesion();
                if (status == Status.OK) {
                    vistaVentana.remove(vistaMenuAnonimo);
                    @SuppressWarnings("unused")
                    ControladorInicio controladorInicio = new ControladorInicio(sistema, vistaVentana);
                }
            }
        });
        
        vistaMenuAnonimo.botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.retrocederCancion();
            }
        });

        vistaMenuAnonimo.botonReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.reanudarCancion();
                vistaMenuAnonimo.botonReproducir.setVisible(false);
                vistaMenuAnonimo.botonParar.setVisible(true);
            }
        });

        vistaMenuAnonimo.botonParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.pausarCancion();
                vistaMenuAnonimo.botonParar.setVisible(false);
                vistaMenuAnonimo.botonReproducir.setVisible(true);
            }
        });

        vistaMenuAnonimo.botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.avanzarCancion();
            }
        });
        
        vistaVentana.repaint();
        
	}
	
}