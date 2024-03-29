package fresh.datos.tipos;

/**
 * Esta enumeración define los posibles estados en los que se puede encontrar
 * una canción.
 */
public enum EstadoCancion {

   /**
    * Estado predeterminado de las canciones al subirse al sistema, el
    * administrador debe revisarla.
    */
   PENDIENTE_VALIDACION("Pendiente de validación"),

   /**
    * Estado de aceptación de la canción, sin detectar contenido explícito.
    */
   VALIDADA("Validada"),

   /**
    * Estado de aceptación de la canción, detectando contenido explicito.
    */
   VALIDADA_EXPLICITA("Validada con contenido explicito"),

   /**
    * Estado de rechazo de la canción. El usuario puede cambiar el fichero MP3
    * asociado para que la canción vuelva a ser evaluada. Si no se realiza dicho
    * cambio en un plazo de 3 días, la canción es eliminada.
    */
   RECHAZADA_REVISABLE("Rechazada - Debe actualizarse"),

   /**
    * Estado de bloqueo preventivo de la canción. Se da cuando una canción sin 
    * contenido explícito es reportada, hasta que el reporte es resuelto.
    */
   BLOQUEADA_TEMPORAL("Bloqueada temporalmente"),

   /**
    * Estado de bloqueo preventivo de la canción. Se da cuando una canción con 
    * contenido explícito es reportada, hasta que el reporte es resuelto.
    */
   BLOQUEADA_TEMPORAL_EXPLICITA("Bloqueada temporalmente"),

   /**
    * Estado de bloqueo permanente. Cuando un reporte es aceptado, el usuario
    * que comete la infracción es baneado, justo a todas sus canciones.
    */
   BLOQUEADA_PERMANENTE("Bloqueada permanentemente");

   private String texto;

   private EstadoCancion(String texto) {
      this.texto = texto;
   }

   public String aTexto() {
      return texto;
   }
}