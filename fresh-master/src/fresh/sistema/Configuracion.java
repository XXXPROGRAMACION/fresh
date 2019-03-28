package fresh.sistema;

import java.io.*;
import java.util.Objects;

import fresh.Status;

public class Configuracion {
    private String ruta;

    private int maxReproduccionesAnonimo;
    private int maxReproduccionesRegistrado;
    private float cuotaPremium;
    private int minReproduccionesPremium;
    private int caracteresMinimos;

    public Configuracion(String ruta) {
        this.ruta = ruta;
        if (cargarConfiguracion() == Status.OK) return;

        maxReproduccionesAnonimo = 20;
        maxReproduccionesRegistrado = 100;
        cuotaPremium = 9.99f;
        minReproduccionesPremium = 1000;
        caracteresMinimos = 4;

        guardarConfiguracion();
    }

    private Status cargarConfiguracion() {
        try (FileInputStream stream = new FileInputStream(ruta);
             InputStreamReader reader = new InputStreamReader(stream);
             BufferedReader buffer = new BufferedReader(reader);) {

            String linea = buffer.readLine();
            if (linea == null) throw new IOException();
            String[] palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "MAX_REPRODUCCIONES_ANONIMO")) throw new IOException();
            maxReproduccionesAnonimo = Integer.parseInt(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "MAX_REPRODUCCIONES_REGISTRADO")) throw new IOException();
            maxReproduccionesRegistrado = Integer.parseInt(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "CUOTA_PREMIUM")) throw new IOException();
            cuotaPremium = Float.parseFloat(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "MIN_REPRODUCCIONES_PREMIUM")) throw new IOException();
            minReproduccionesPremium = Integer.parseInt(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "CARACTERES_MINIMOS")) throw new IOException();
            caracteresMinimos = Integer.parseInt(palabras[1]);

            return Status.OK;
        } catch (IOException e) {
            return Status.ERROR_CARGAR;
        } catch (NumberFormatException e) {
            return Status.ERROR_CARGAR;
        }
    }

    private void guardarConfiguracion() {
        try (FileOutputStream stream = new FileOutputStream(ruta);
             OutputStreamWriter writer = new OutputStreamWriter(stream);
             BufferedWriter buffer = new BufferedWriter(writer);) {

            buffer.write("MAX_REPRODUCCIONES_ANONIMO " + maxReproduccionesAnonimo + "\n");
            buffer.write("MAX_REPRODUCCIONES_REGISTRADO " + maxReproduccionesRegistrado + "\n");
            buffer.write("CUOTA_PREMIUM " + cuotaPremium + "\n");
            buffer.write("MIN_REPRODUCCIONES_PREMIUM " + minReproduccionesPremium + "\n");
            buffer.write("CARACTERES_MINIMOS " + caracteresMinimos);
        } catch (IOException e) {
            return;
        }
    }
}