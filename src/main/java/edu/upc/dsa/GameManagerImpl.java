package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    protected List<Usuario> usuario;
    HashMap<String, String> usuarioMonedero = new HashMap<>();
    HashMap<String, String> Juegos =new HashMap<String,String>();
    LinkedList<String> listaJuegos = new LinkedList<String>();
    LinkedList<String> listaUsuarios = new LinkedList<String>();
    LinkedList<String> listaProductos = new LinkedList<String>();
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    private GameManagerImpl() {
        this.usuario = new LinkedList<>();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }
    @Override
    public String crearJuego(String id, int N_Equipos, int P_personas) {
            String estado = new Partida().getEstado();
            if (listaJuegos.contains(id)){
                logger.error("Ya hay un juego en marcha con esta id: " + id);
                estado = "NO_INICIADO";
            }
            else {
                Juego juegoNuevo = new Juego(id,N_Equipos, P_personas);
                listaJuegos.add(id);
                logger.info("Juego creado con: " + N_Equipos+ "de: "+P_personas+" personas ");
                estado = "INICIADO_EN_PREPARACIÓN";

            }
            return estado;

    }
    @Override
    public void addUsuario(String id, String nombre, String apellido) {
        Usuario usuario;
        int DSA_coins = 25;
        String monedero = id+"/"+ DSA_coins;
        logger.info("Comprobamos si el usuario con id: "+id+" existe");
        if(listaUsuarios.contains(id)) {
            logger.error("Este usuario ya existe");
        }
        else {
            logger.info("El usuario no existe, lo añadimos con la id: "+id);
            usuario = new Usuario(id,nombre,apellido);
            usuarioMonedero.put(id,monedero);
            listaUsuarios.addLast(id);
        }
    }
    @Override
    public void addProducto(String id, String descripcion, double precio) {
        Producto producto;
        logger.info("Comprobamos si el producto con id: "+id+" existe");
        if(listaProductos.contains(id)) {
            logger.error("Este producto ya existe");
        }
        else {
            logger.info("El producto no existe, lo añadimos con la id: "+id);
            producto = new Producto(id,descripcion,precio);
            listaProductos.addLast(id);
        }
    }

    @Override
    public void comprarProducto(String idProducto, String idUsuario)  {
        logger.info("Comprobamos si el producto con id:"+idProducto+"y el usuario con id:"+idUsuario+"existen");
        if(listaUsuarios.contains(idUsuario) && listaProductos.contains(idProducto)){

        }
        else{
            logger.info("El producto o usuario no existen");
        }
    }
    @Override
    public String iniciarPartida(String idJuego, String idUsuario) {
        String estado = new Partida().getEstado();
        boolean encontrado = false;
        if(listaJuegos.contains(idJuego) && listaUsuarios.contains(idUsuario)) {
            logger.info("Ya existe un juego con este identificador: " + idJuego + " jugado por el jugador con id: " + idUsuario);
            if (Juegos.containsValue(idJuego) && idJuego.equals(Juegos.get((idUsuario)))) {
                logger.error("El usuario ya esta en una partida");

            }
            else {
                estado = "INICIADO_EN_FUNCIONAMIENTO";
                encontrado = true;
            }
        }

        else {
            logger.error("No exite el juego o el usuario");
        }
        if (encontrado){
            logger.info("Se ha iniciado partida correctamente");
            Juegos.put(idJuego,idUsuario);
        }
        return estado;
    }
    public String consultarEstado(String idJuego){
        String resultado = null;
        String estado = new Partida().getEstado();
        if(listaJuegos.contains(idJuego)) {
            logger.info("Hay una partida con id:"+idJuego);
            resultado = estado;
        }
        else{
            logger.error("La partida no existe");
        }
        return resultado;

    }
    @Override
    public int consultarVida(String idUsuario) {
        int resultado=0;
        if(listaUsuarios.contains(idUsuario) && Juegos.containsKey(idUsuario)) {
            logger.info("El usuario con id: " + idUsuario + "está en una partida");
            int vida = new Partida().getVida();
            resultado = vida;
        }
        else{
            logger.error("El usuario no existe o no está en partida");
            resultado = -1;
        }
        return resultado;
    }
    @Override
    public String finalizarJuego(String idJuego) {
        String estado = new Partida().getEstado();
        if (listaJuegos.contains(idJuego) && Juegos.containsKey(idJuego)){
            logger.info("Hay una partida en marca con el id: "+idJuego);
            for(Map.Entry<String,String> entry : Juegos.entrySet()) {
                if (entry.getKey().equals(idJuego)) {
                    Juegos.remove(idJuego);
                    logger.info("Se ha finalizado el partida");
                    estado = "FINALIZADO";
                    break;
                }
            }
        }
        else{
            logger.error("No existe el usuario o no se encuentra en partida");
        }
        return  estado;
    }
    @Override
    public int numUsuarios() {
        return this.listaUsuarios.size();
    }

    @Override
    public int numProductos() {
        return this.listaProductos.size();
    }

}