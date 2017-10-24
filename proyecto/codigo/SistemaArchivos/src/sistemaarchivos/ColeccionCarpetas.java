
package sistemaarchivos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * Esta clase contiene la tabla de hash que permite la eficiencia de las
 * búsquedas de datos.
 *
 * @author ljpalaciom
 */
public class ColeccionCarpetas {
    
    private HashMap tabla;

    /**
     * Este es el constructor que inicializa el HashMap.
     *
     */
    public ColeccionCarpetas() {
        tabla = new HashMap<>();
    }

    /**
     * Este metodo permite ingresar un nuevo dato a la tabla de hash.
     *
     * @param clave Esta es el nombre que se asocia con un valor de tipo carpeta o
     * archivo, asi cuando se busque la clave se obtiene este valor rapidamente.
     * @param carpeta
     */
    public void put(String clave, Carpeta carpeta) {
        LinkedList<Carpeta> auxiliar = (LinkedList<Carpeta>) tabla.get(clave);
        if (auxiliar == null) {
            auxiliar = new LinkedList<>();
        }
        auxiliar.add(carpeta);
        tabla.put(clave, auxiliar);
    }

    /**
     * Este metodo permite obtener una lista de la tabla de hash. Retorna null
     * si no lo encuentra.
     *
     * @param clave El string que se asocia con un valor de tipo carpeta o
     * archivo.
     * @return Una LinkedList con todos las carpetas de coincidan con la clave
     */
    public LinkedList<Carpeta> get(String clave) {
        LinkedList<Carpeta> retornar = (LinkedList<Carpeta>) tabla.get(clave);
        if (retornar == null) {
            System.out.println("No such file or directory");
        }
        return retornar;
    }

    /**
     * Este metodo permite una carpeta usando el parametro direccion. Retorna
     * null si no lo encuentra.
     *
     * @param clave
     * @param direccion La direccion del archivo
     * @return
     */
    public Carpeta get(String clave, String direccion) {
        String div[] = direccion.split("/");

        LinkedList<Carpeta> retornar = (LinkedList<Carpeta>) tabla.get(clave);
        LinkedList<String> dir = new LinkedList<>();
        for (int i = 0; i < div.length; i++) {
            dir.add(div[i]);
        }
        if (retornar == null) {
            System.out.println("No such file or directory");
        }
        for (Carpeta carpeta : retornar) {
            if (carpeta.getDireccion().hashCode() == dir.hashCode()) {
                return carpeta;
            }
        }
        return null;
    }

    /**
     * Este metodo está hecho para retornar todos las rutas de una lista de
     * carpetas
     *
     * @param coincidencias La lista de coincidencias que se analizará
     * @return Este metodo retorna una lista enlazada de listas de cadenas de
     * caracteres.
     */
    public LinkedList<LinkedList<String>> direcciones(LinkedList<Carpeta> coincidencias) {
        LinkedList<LinkedList<String>> retornar = new LinkedList<>();
        for (Carpeta carpeta : coincidencias) {
            retornar.add(carpeta.getDireccion());
        }
        return retornar;
    }

    /**
     * Este metodo está hecho para retornar todos los contenidos de una lista de
     * carpetas
     *
     * @param coincidencias
     * @return Este metodo retorna una lista enlazada de listas de cadenas de
     * caracteres.
     */
    public LinkedList<LinkedList<String>> contenidos(LinkedList<Carpeta> coincidencias) {
        LinkedList<LinkedList<String>> retornar = new LinkedList<>();
        if (coincidencias != null) {
            for (Carpeta carpeta : coincidencias) {
                if (carpeta.getTipo() == TipoCarpeta.Carpeta) {
                    retornar.add(carpeta.listarContenido());
                }
            }
        }
        return retornar;
    }

    /**
     * Este metodo está hecho para retornar los contenidos de un tamaño mayor al
     * ingresado por parámetro de una lista de carpetas
     *
     * @param coincidencias
     * @param TamanoMayor
     * @return Este metodo retorna una lista enlazada de listas de cadenas de
     * caracteres.
     */
    public LinkedList<LinkedList<String>> contenidosMayor(LinkedList<Carpeta> coincidencias, String TamanoMayor) {
        LinkedList<LinkedList<String>> retornar = new LinkedList<>();
        if (coincidencias != null) {
            for (Carpeta carpeta : coincidencias) {
                if (carpeta.getTipo() == TipoCarpeta.Carpeta) {
                    retornar.add(carpeta.listarContenidoMayor(TamanoMayor));
                }
            }
        }
        return retornar;
    }

    /**
     * Este metodo está hecho para retornar los contenidos de un usuario
     * ingresado por parámetro de una lista de carpetas
     *
     * @param coincidencias Una lista enlazada de carpetas
     * @param Usuario El dueño del directorio a buscar
     * @return Este metodo retorna una lista enlazada de listas de cadenas de
     * caracteres.
     */
    public LinkedList<LinkedList<String>> contenidosUsuario(LinkedList<Carpeta> coincidencias, String Usuario) {
        LinkedList<LinkedList<String>> retornar = new LinkedList<>();
        if (coincidencias != null) {
            for (Carpeta carpeta : coincidencias) {
                if (carpeta.getTipo() == TipoCarpeta.Carpeta) {
                    retornar.add(carpeta.listarContenidoUsuario(Usuario));
                }
            }
        }
        return retornar;
    }

    /**
     * Usa el metodo toString de la clase Carpeta con una lista de carpetas
     *
     * @param coincidencias
     */
    public void imprimirCarpetas(LinkedList<Carpeta> coincidencias) {
        for (Carpeta coincidencia : coincidencias) {
            System.out.println(coincidencia);
        }
    }
    
}
