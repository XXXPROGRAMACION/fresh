package fresh.sistema;

import fresh.datos.BaseDeDatos;
import fresh.datos.tipos.*;
import fresh.Status;
import java.time.LocalDate;
import java.util.Objects;
import java.util.List;

public class Sistema {
    private ModoEjecucion modoEjecucion;
    private Usuario usuarioActual;
    private BaseDeDatos baseDeDatos;
    private int reproduccionesSesion;

    public Sistema() {
        modoEjecucion = ModoEjecucion.DESCONECTADO;
        baseDeDatos = BaseDeDatos.cargarBaseDeDatos("./baseDedatos/baseDeDatos.bd");
    }

    // FALTA COMPROBAR CREDENCIALES DEL ADMINISTRADOR
    public Status iniciarSesion(String nombre, String contrasena) {
        if (modoEjecucion != ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        Usuario usuario = baseDeDatos.buscarUsuario(nombre);
        if (usuario == null) return Status.NOMBRE_INVALIDO;
        if (!Objects.equals(contrasena, usuario.getContrasena())) return Status.CONTRASENA_INVALIDA;

        modoEjecucion = ModoEjecucion.REGISTRADO;
        usuarioActual = usuario;
        
        return Status.OK;
    }

    public Status iniciarSesionAnonimo() {
        if (modoEjecucion != ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        modoEjecucion = ModoEjecucion.ANONIMO;
        reproduccionesSesion = 0;

        return Status.OK;
    }

    public Status registrarse(String nombre, String nombreAutor, String contrasena, LocalDate fechaNacimiento) {
        if (modoEjecucion != ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        if (nombre.length() < 4) return Status.NOMBRE_INVALIDO;
        if (nombreAutor.length() < 4) return Status. NOMBRE_AUTOR_INVALIDO;
        if (contrasena.length() < 4) return Status.CONTRASENA_INVALIDA;
        if (false /*comprobación edad*/) return Status.EDAD_INVALIDA;

        if (baseDeDatos.buscarUsuario(nombre) != null) return Status.USUARIO_REPETIDO;

        baseDeDatos.anadirUsuario(new Usuario(nombre, nombreAutor, contrasena, fechaNacimiento));

        return Status.OK;
    }

    public Status cerrarSesion() {
        if (modoEjecucion == ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        modoEjecucion = ModoEjecucion.DESCONECTADO;

        return Status.OK;
    }

    // public Status reproducir();

    // public Status reanudarCancion();

    // public Status pausarCancion();

    // public Status pararCancion();

    public List<Usuario> buscarAutores(String nombreAutor) {
        return baseDeDatos.buscarUsuarios(nombreAutor);
    }

    public Status seguirAutor(Usuario autor) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        autor.anadirSeguidor(usuarioActual);

        return Status.OK;
    }

    public List<Cancion> buscarCanciones(String nombre) {
        return baseDeDatos.buscarCanciones(nombre);
    }

    // HAY QUE ASOCIAR EL MP3
    public Status subirCancion(String nombre) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        if (nombre.length() < 4) return Status.NOMBRE_INVALIDO;
        long id = baseDeDatos.getIdSiguienteCancion();
        long duracion = 0; // Actualizar utilizando el módulo MP3
        Cancion cancion = new Cancion(nombre, duracion, usuarioActual, id);

        return baseDeDatos.anadirCancion(cancion);
    }

    public void eliminarCancion(Cancion cancion) {
        baseDeDatos.eliminarCancion(cancion);
    }

    // public Status actualizarCancion();

    public List<Album> buscarAlbumes(String nombre) {
        return baseDeDatos.buscarAlbumes(nombre);
    }

    public Status crearAlbum(String nombre, int ano, List<Cancion> canciones) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        if (nombre.length() < 4) return Status.NOMBRE_INVALIDO;
        if (canciones.size() == 0) return Status.ALBUM_VACIO;

        Album album = new Album(nombre, usuarioActual, ano, canciones);

        return baseDeDatos.anadirAlbum(album);
    }

    public void eliminarAlbum(Album album) {
        baseDeDatos.eliminarAlbum(album);
    }

    public Status crearListaReproduccion(String nombre) {
        if (nombre.length() < 4) return Status.NOMBRE_INVALIDO;

        ListaReproduccion listaReproduccion = new ListaReproduccion(nombre);

        return usuarioActual.anadirListaReproduccion(listaReproduccion);
    }

    public Status anadirAListaReproduccion(ListaReproduccion listaReproduccion, ElementoReproducible elemento) {
        return listaReproduccion.anadirElemento(elemento);
    }

    public void eliminarDeListaReproduccion(ListaReproduccion listaReproduccion, ElementoReproducible elemento) {
        listaReproduccion.eliminarElemento(elemento);
    }

    public void eliminarListaReproduccion(ListaReproduccion listaReproduccion) {
        usuarioActual.eliminarListaReproduccion(listaReproduccion);
    }

    // public Status validarCancion();

    // public Status reportar();

    // public Status valorarReporte();

    // public Status pagarPremium();

    // public Status modificarConfiguracion();
}