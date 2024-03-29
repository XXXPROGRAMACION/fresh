package fresh.modulos;

import fresh.Status;
import fresh.sistema.Configuracion;
import fresh.sistema.ModoEjecucion;
import fresh.datos.tipos.*;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.player.Player;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

public class ModuloMP3 implements Runnable {
    private String ruta;
    private Configuracion configuracion;
    private ModoEjecucion modoEjecucion;
    private Usuario usuarioActual;
    private int reproduccionesSesion;
    private List<Cancion> canciones;
    private boolean reproduciendo;
    private boolean nuevaCancion;
    private Integer posicionActual;
    private Cancion cancionActual;
    private Player player;
    private boolean parar;

    /**
     * Instancia un módulo MP3 con la configuración especificada.
     * @param ruta Ruta de los ficheros MP3
     * @param configuracion Configuración de la aplicación
     */
    public ModuloMP3(String ruta, Configuracion configuracion) {
        this.ruta = ruta;
        this.configuracion = configuracion;
        canciones = Collections.synchronizedList(new LinkedList<>());
        posicionActual = 0;
        reproduciendo = false;
        nuevaCancion = false;
        parar = false;
    }

    @Override
    public void run() {
        try {        	
            while (!parar) {
            	System.out.print("");
                if (reproduciendo) {
                    if (!player.play(1)) {
                        reproduciendo = false;
                        
                        synchronized (posicionActual) {
                            posicionActual++;
                        }

                        nuevaCancion = true;
                    }
                } else {                    
                    if (nuevaCancion && posicionActual < canciones.size()) {
                        if (modoEjecucion == ModoEjecucion.REGISTRADO) {
                            int reproduccionesMensuales = usuarioActual.getReproduccionesMensuales();
                            
                            if (!usuarioActual.getPremium()) {
                                if (reproduccionesMensuales >= configuracion.getMaxReproduccionesRegistrado()) {
                                    continue;
                                } else if (reproduccionesMensuales+1 == configuracion.getMaxReproduccionesRegistrado()) {
                                    usuarioActual.anadirNotificacion(new Notificacion(TipoNotificacion.LIMITE_REPRODUCCIONES));
                                }
                            }

                            usuarioActual.setReproduccionesMensuales(reproduccionesMensuales+1);
                        } else if (modoEjecucion == ModoEjecucion.ANONIMO) {
                            if (reproduccionesSesion >= configuracion.getMaxReproduccionesAnonimo()) continue;
                            reproduccionesSesion++;
                        }
                        
                        cancionActual = canciones.get(posicionActual);

                        if (!usuarioActual.equals(cancionActual.getAutor())) {
                            cancionActual.setReproduccionesMensuales(cancionActual.getReproduccionesMensuales()+1);
                        }

                        String rutaCancion = ruta + canciones.get(posicionActual).getId() + ".mp3";
                        player = new Player(new FileInputStream(rutaCancion));
                        nuevaCancion = false;
                        reproduciendo = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Añade las canciones al reproductor.
     * @param canciones Canciones a añadir al reproductor
     */
    public void anadirCanciones(List<Cancion> canciones) {
        synchronized (posicionActual) {        
            posicionActual = this.canciones.size();
        }

        this.canciones.addAll(canciones);
        reproduciendo = false;
        nuevaCancion = true;
    }

    /**
     * Activa la reproducción de MP3.
     * @return "NO_PUEDE_REPRODUCIR" si no hay nada que reproducir.
     * "OK" en caso contrario.
     */
    public Status reproducir() {
        if (posicionActual == canciones.size()) return Status.NO_PUEDE_REPRODUCIR;
        
        reproduciendo = true;
        return Status.OK;
    }

    /**
     * Pausa la reproducción de MP3.
     */
    public void pausar() {
        reproduciendo = false;
    }

    /**
     * Reinicia la canción actual.
     */
    public void reiniciar() {
        if (posicionActual == canciones.size()) return;

        reproduciendo = false;
        nuevaCancion = true;
    }

    /**
     * Avanza a la siguiente canción.
     * @return "NO_PUEDE_REPRODUCIR" si no hay nada que reproducir.
     * "OK" en caso contrario.
     */
    public Status avanzar() {
        if (posicionActual >= canciones.size()-1) return Status.NO_PUEDE_REPRODUCIR;

        reproduciendo = false;
        nuevaCancion = true;

        synchronized (posicionActual) {        
            posicionActual++;
        }

        return Status.OK;
    }

    /**
     * Retrocede a la anterior canción.
     * @return "NO_PUEDE_REPRODUCIR" si no hay nada que reproducir.
     * "OK" en caso contrario.
     */
    public Status retroceder() {
        if (posicionActual <= 0) return Status.NO_PUEDE_REPRODUCIR;

        reproduciendo = false;
        nuevaCancion = true;

        synchronized (posicionActual) {        
            posicionActual--;
        }

        return Status.OK;
    }

    /**
     * Establece el modo de ejecución de usuario anónimo y reinicia las
     * reproducciones de la sesión.
     */
    public void nuevaSesionAnonimo() {
        modoEjecucion = ModoEjecucion.ANONIMO;
        reproduccionesSesion = 0;
    }

    /**
     * Establece el modo de ejecución de usuario anónimo y establece las
     * reproducciones actuales de la sesión.
     * @param reproduccionesSesion Reproducciones actuales de la sesión
     */
    public void nuevaSesionAnonimo(int reproduccionesSesion) {
        modoEjecucion = ModoEjecucion.ANONIMO;
        this.reproduccionesSesion = reproduccionesSesion;
    }

    /**
     * Establece el modo de ejecución de usuario registrado y actualiza al
     * usuario actual.
     * @param usuarioActual Nuevo usuario actual
     */
    public void nuevaSesionRegistrado(Usuario usuarioActual) {
        modoEjecucion = ModoEjecucion.REGISTRADO;
        this.usuarioActual = usuarioActual;
    }

    /**
     * Calcula la duración de un fichero de audio.
     * @param fichero Archivo de audio
     * @return Duración del fichero de audio en segundos.
     */
    public static long obtenerDuracion(File fichero) {
    	FileInputStream fis;
        Bitstream bitstream;
        Header h = null;
        long tn = 0;

        try {
        	fis = new FileInputStream(fichero);
        	bitstream = new Bitstream(fis);
            h = bitstream.readFrame();
            tn = fis.getChannel().size();
        } catch (Exception e) {

        }
        
        return (long) h.total_ms((int) tn)/1000;
    }
    
    /**
     * Valida un fichero de audio.
     * @param fichero Archivo a validar
     * @return "true" si el archivo de audio es correcto y "false" si no lo es.
     */
    public static boolean validar(File fichero) {
        AudioFileFormat fileFormat;
        
    	try {
    		fileFormat = AudioSystem.getAudioFileFormat(fichero);
    	} catch (IOException e) {
    		return false;
    	} catch (UnsupportedAudioFileException e) {
    		return false;
    	}
    	
        if (fileFormat instanceof TAudioFileFormat) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve al módulo MP3 a su estado inicial
     */
    public void clear() {
        reproduciendo = false;
        posicionActual = 0;
        nuevaCancion = false;
        
        canciones.clear();
    }
    
    /**
     * Finaliza la ejecución del hilo.
     */
    public void finalizar() {
    	parar = true;
    }
}