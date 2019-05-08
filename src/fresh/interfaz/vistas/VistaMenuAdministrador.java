package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;

/**
 * Esta vista define el menu del administrador
 */
public class VistaMenuAdministrador extends JPanel {
	
	private static final long serialVersionUID = 0;

    public JPanel panelSuperior;
    public JLabel textoNombreAplicacion;
    public JCustomTextField entradaBusqueda;
    public JCustomComboBox seleccionModoBusqueda;
    public JCustomButton botonBuscar;
    
    public JPanel panelLateral;
    public JCustomButton botonReportes;
    public JCustomButton botonNuevasCanciones;
    public JCustomButton botonConfiguracion;
    public JLabel textoAdministrador;
    public JCustomButton botonCerrarSesion;

    public JPanel panelActual;

    public JPanel panelInferior;
    public JCustomButton botonReproducir;
    public JCustomButton botonParar;
    public JCustomButton botonAnterior;
    public JCustomButton botonSiguiente;
    
    public VistaMenuAdministrador() {
    	
    	// Crea y configura el panel principal
        setBounds(0, 0, Estilo.anchura, Estilo.altura);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        // Configura el panel superior
        panelSuperior = new JPanel();        
        panelSuperior.setBounds(260, 0, Estilo.anchura-260, 120);        
        panelSuperior.setOpaque(false);
        panelSuperior.setLayout(null);
        add(panelSuperior);

        entradaBusqueda = new JCustomTextField("", 10);
        entradaBusqueda.setPlaceholder("Buscar...");
        entradaBusqueda.setBounds(35, 30, 420, 80);
        entradaBusqueda.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaBusqueda.setForeground(Estilo.colorTexto);
        entradaBusqueda.setBackground(Estilo.colorTerciario);
        entradaBusqueda.setMarginSize(25);
        entradaBusqueda.setCornerRadius(40);
        entradaBusqueda.setShadowSize(5);
        entradaBusqueda.setShadowOpacity(0.2f);
        panelSuperior.add(entradaBusqueda);

        String[] opciones = {"Canciones", "Álbumes", "Autores"};
        seleccionModoBusqueda = new JCustomComboBox(opciones);
        seleccionModoBusqueda.setBounds(470, 30, 180, 80);
        seleccionModoBusqueda.setMainFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        seleccionModoBusqueda.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        seleccionModoBusqueda.setForeground(Estilo.colorTexto);
        seleccionModoBusqueda.setBackground(Estilo.colorTerciario);
        seleccionModoBusqueda.setBehindColor(Estilo.colorPrimario);
        seleccionModoBusqueda.setMarginSize(25);
        seleccionModoBusqueda.setCornerRadius(40);
        seleccionModoBusqueda.setShadowSize(5);
        seleccionModoBusqueda.setShadowOpacity(0.2f);
        panelSuperior.add(seleccionModoBusqueda);

        botonBuscar = new JCustomButton("🔎");
        botonBuscar.setBounds(665, 30, 80, 80);
        botonBuscar.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 20));
        botonBuscar.setForeground(Estilo.colorTexto);
        botonBuscar.setBackground(new Color(240, 240, 100));
        botonBuscar.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonBuscar.setCornerRadius(40);
        botonBuscar.setHeight(5);
        botonBuscar.setShadowSize(5);
        botonBuscar.setShadowOpacity(0.4f);
        panelSuperior.add(botonBuscar);

        // Configura el panel lateral
        panelLateral = new JPanel();        
        panelLateral.setBounds(0, 0, 260, Estilo.altura);        
        panelLateral.setBackground(Estilo.colorSecundario);
        panelLateral.setLayout(null);
        add(panelLateral);

        textoNombreAplicacion = new JLabel("FRESH");
        textoNombreAplicacion.setBounds(0, 40, 260, 40);
        textoNombreAplicacion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 50));
        textoNombreAplicacion.setForeground(Estilo.colorTexto);
        textoNombreAplicacion.setHorizontalAlignment(JLabel.CENTER);
        panelLateral.add(textoNombreAplicacion);

        botonReportes = new JCustomButton("Reportes");
        botonReportes.setBounds(20, 120, 220, 80);
        botonReportes.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonReportes.setForeground(Estilo.colorTexto);
        botonReportes.setBackground(new Color(240, 240, 100));
        botonReportes.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonReportes.setCornerRadius(40);
        botonReportes.setHeight(5);       
        botonReportes.setShadowSize(5);
        botonReportes.setShadowOpacity(0.4f);
        panelLateral.add(botonReportes);

        botonNuevasCanciones = new JCustomButton("Nuevas canciones");
        botonNuevasCanciones.setBounds(20, 205, 220, 80);
        botonNuevasCanciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonNuevasCanciones.setForeground(Estilo.colorTexto);
        botonNuevasCanciones.setBackground(new Color(240, 240, 100));
        botonNuevasCanciones.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonNuevasCanciones.setCornerRadius(40);
        botonNuevasCanciones.setHeight(5);       
        botonNuevasCanciones.setShadowSize(5);
        botonNuevasCanciones.setShadowOpacity(0.4f);
        panelLateral.add(botonNuevasCanciones);

        botonConfiguracion = new JCustomButton("Configuracion");
        botonConfiguracion.setBounds(20, 290, 220, 80);
        botonConfiguracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonConfiguracion.setForeground(Estilo.colorTexto);
        botonConfiguracion.setBackground(new Color(240, 240, 100));
        botonConfiguracion.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonConfiguracion.setCornerRadius(40);
        botonConfiguracion.setHeight(5);       
        botonConfiguracion.setShadowSize(5);
        botonConfiguracion.setShadowOpacity(0.4f);
        panelLateral.add(botonConfiguracion);
        
        textoAdministrador = new JLabel("Administrador");
        textoAdministrador.setBounds(20, 645, 220, 80);
        textoAdministrador.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoAdministrador.setForeground(Estilo.colorTexto);
        textoAdministrador.setHorizontalAlignment(JLabel.CENTER);
        panelLateral.add(textoAdministrador);

        botonCerrarSesion = new JCustomButton("Cerrar sesión");
        botonCerrarSesion.setBounds(20, 705, 220, 80);
        botonCerrarSesion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonCerrarSesion.setForeground(Estilo.colorTexto);
        botonCerrarSesion.setBackground(new Color(245, 100, 100));
        botonCerrarSesion.setPressedBackgound(new Color(220, 50, 50).brighter());
        botonCerrarSesion.setCornerRadius(40);
        botonCerrarSesion.setHeight(5);       
        botonCerrarSesion.setShadowSize(5);
        botonCerrarSesion.setShadowOpacity(0.4f);
        panelLateral.add(botonCerrarSesion);

        panelActual = null;

        // Configura el panel inferior
        panelInferior = new JPanel();        
        panelInferior.setBounds(260, 680, Estilo.anchura-260, Estilo.altura-680);      
        panelInferior.setBackground(Estilo.colorPrimario.darker());
        panelInferior.setLayout(null);
        add(panelInferior);

        botonAnterior = new JCustomButton("⏪");
        botonAnterior.setBounds((Estilo.anchura-260-100)/2-100, 20, 100, 80);
        botonAnterior.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 35));
        botonAnterior.setForeground(Estilo.colorTexto);
        botonAnterior.setBackground(new Color(240, 240, 100));
        botonAnterior.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonAnterior.setCornerRadius(80);
        botonAnterior.setHeight(5);       
        botonAnterior.setShadowSize(5);
        botonAnterior.setShadowOpacity(0.4f);
        panelInferior.add(botonAnterior);

        botonReproducir = new JCustomButton("▶");
        botonReproducir.setBounds((Estilo.anchura-260-100)/2, 20, 100, 80);
        botonReproducir.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 35));
        botonReproducir.setForeground(Estilo.colorTexto);
        botonReproducir.setBackground(new Color(240, 240, 100));
        botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonReproducir.setCornerRadius(80);
        botonReproducir.setHeight(5);       
        botonReproducir.setShadowSize(5);
        botonReproducir.setShadowOpacity(0.4f);
        panelInferior.add(botonReproducir);

        botonParar = new JCustomButton("⏸");
        botonParar.setBounds((Estilo.anchura-260-100)/2, 20, 100, 80);
        botonParar.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 35));
        botonParar.setForeground(Estilo.colorTexto);
        botonParar.setBackground(new Color(240, 240, 100));
        botonParar.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonParar.setCornerRadius(80);
        botonParar.setHeight(5);
        botonParar.setShadowSize(5);
        botonParar.setShadowOpacity(0.4f);
        botonParar.setVisible(false);
        panelInferior.add(botonParar);

        botonSiguiente = new JCustomButton("⏩");
        botonSiguiente.setBounds((Estilo.anchura-260-100)/2+100, 20, 100, 80);
        botonSiguiente.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 35));
        botonSiguiente.setForeground(Estilo.colorTexto);
        botonSiguiente.setBackground(new Color(240, 240, 100));
        botonSiguiente.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonSiguiente.setCornerRadius(80);
        botonSiguiente.setHeight(5);       
        botonSiguiente.setShadowSize(5);
        botonSiguiente.setShadowOpacity(0.4f); 
        panelInferior.add(botonSiguiente);
        
        // Revela la ventana de la aplicación 
        setVisible(true);
    }
}
