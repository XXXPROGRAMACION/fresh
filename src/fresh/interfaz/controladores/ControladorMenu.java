package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Este controlador carga la funcionalidad del menú de los usuarios registrados.
 */
public class ControladorMenu {

    public ControladorMenu(Sistema sistema, VistaVentana vistaVentana) {
        VistaMenu vistaMenu = new VistaMenu();
        vistaVentana.add(vistaMenu);
        
        vistaMenu.textoNombreUsuario.setText(sistema.getUsuarioActual().getNombre());
        
        if (sistema.getUsuarioActual().getPremium()) {
        	vistaMenu.textoPremiumUsuario.setText("Usuario premium");
        	vistaMenu.botonPagarPremium.setVisible(false);
        } else {
        	vistaMenu.textoPremiumUsuario.setText("Usuario no premium");
        	vistaMenu.botonPagarPremium.setVisible(true);
        }

        vistaMenu.repaint();
        
        vistaMenu.seleccionModoBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;

                    vistaMenu.repaint();
                }
            }
        });

        vistaMenu.botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = vistaMenu.entradaBusqueda.getText();
                if (entrada.length() == 0) return;

                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }

                if (vistaMenu.seleccionModoBusqueda.getSelectedIndex() == 0) {
                    // Canciones
                    VistaResultadoCanciones vistaResultadoCanciones = new VistaResultadoCanciones(entrada);
                    vistaMenu.panelActual = vistaResultadoCanciones;
                    vistaMenu.add(vistaResultadoCanciones);

                    @SuppressWarnings("unused")
                    ControladorResultadoCanciones controladorResultadoCanciones = new ControladorResultadoCanciones(sistema, vistaResultadoCanciones, vistaMenu, entrada);
                
                    vistaResultadoCanciones.setVisible(true);
                } else if (vistaMenu.seleccionModoBusqueda.getSelectedIndex() == 1) {
                    // Álbumes
                    VistaResultadoAlbumes vistaResultadoAlbumes = new VistaResultadoAlbumes(entrada);
                    vistaMenu.panelActual = vistaResultadoAlbumes;
                    vistaMenu.add(vistaResultadoAlbumes);

                    @SuppressWarnings("unused")
                    ControladorResultadoAlbumes controladorResultadoAlbumes = new ControladorResultadoAlbumes(sistema, vistaResultadoAlbumes, vistaMenu, entrada);
                
                    vistaResultadoAlbumes.setVisible(true);
                } else {
                    // Autores
                    VistaResultadoAutores vistaResultadoAutores = new VistaResultadoAutores(entrada);
                    vistaMenu.panelActual = vistaResultadoAutores;
                    vistaMenu.add(vistaResultadoAutores);

                    @SuppressWarnings("unused")
                    ControladorResultadoAutores controladorResultadoAutores = new ControladorResultadoAutores(sistema, vistaResultadoAutores, vistaMenu, entrada);
                
                    vistaResultadoAutores.setVisible(true);
                }

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);                    
                    vistaMenu.panelActual = null;
                }
                
                VistaPlaylists vistaPlaylists = new VistaPlaylists();
                vistaMenu.panelActual = vistaPlaylists;
                vistaMenu.add(vistaPlaylists);

                @SuppressWarnings("unused")
                ControladorPlaylists controladorPlaylists = new ControladorPlaylists(sistema, vistaPlaylists, vistaMenu);

                vistaPlaylists.setVisible(true);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonAutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }
                
                VistaAutores vistaAutores = new VistaAutores();
                vistaMenu.panelActual = vistaAutores;
                vistaMenu.add(vistaAutores);

                @SuppressWarnings("unused")
                ControladorAutores controladorAutores = new ControladorAutores(sistema, vistaAutores);

                vistaAutores.setVisible(true);
                
                vistaMenu.repaint();
            }
        });

        vistaMenu.botonMisCanciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }
                
                VistaCanciones vistaCanciones = new VistaCanciones();
                vistaMenu.panelActual = vistaCanciones;
                vistaMenu.add(vistaCanciones);

                @SuppressWarnings("unused")
                ControladorCanciones controladorCanciones = new ControladorCanciones(sistema, vistaCanciones, vistaMenu);

                vistaCanciones.setVisible(true);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonMisAlbumes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
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

        vistaMenu.botonNotificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }
                
                VistaNotificaciones VistaNotificaciones = new VistaNotificaciones();
                vistaMenu.panelActual = VistaNotificaciones;
                vistaMenu.add(VistaNotificaciones);
                
                @SuppressWarnings("unused")
                ControladorNotificaciones controladorNotificaciones = new ControladorNotificaciones(sistema, VistaNotificaciones);

                VistaNotificaciones.setVisible(true);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonPagarPremium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }
            	
            	 VistaPagarPremium vistaPagarPremium = new VistaPagarPremium();
                 vistaMenu.panelActual = vistaPagarPremium;
                 vistaMenu.add(vistaPagarPremium);
                 
                 @SuppressWarnings("unused")
                 ControladorPagarPremium controladorPagarPremium = new ControladorPagarPremium(sistema, vistaVentana, vistaMenu, vistaPagarPremium);

                 vistaPagarPremium.setVisible(true);
                 vistaMenu.repaint();

            }
        });

        vistaMenu.botonCerrarSesion.addActionListener(new ActionListener() {
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

        vistaMenu.botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sistema.retrocederCancion() != Status.OK) return;
                vistaMenu.botonReproducir.setVisible(false);
                vistaMenu.botonParar.setVisible(true);
            }
        });

        vistaMenu.botonReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sistema.reanudarCancion() != Status.OK) return;
                vistaMenu.botonReproducir.setVisible(false);
                vistaMenu.botonParar.setVisible(true);
            }
        });

        vistaMenu.botonParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.pausarCancion();
                vistaMenu.botonParar.setVisible(false);
                vistaMenu.botonReproducir.setVisible(true);
            }
        });

        vistaMenu.botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sistema.avanzarCancion() != Status.OK) return;
                vistaMenu.botonReproducir.setVisible(false);
                vistaMenu.botonParar.setVisible(true);
            }
        });
        
        vistaVentana.repaint();
    }
}