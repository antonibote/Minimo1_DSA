package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    private Juego juego;
    protected Map<String, Usuario> usuario;
    HashMap<String, String> Juegos =new HashMap<String,String>();
    LinkedList<String> listaJuegos = new LinkedList<String>();
    LinkedList<String> listaUsuarios = new LinkedList<String>();
    LinkedList<String> listaProductos = new LinkedList<String>();
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    public GameManagerImpl() {
        this.usuario = new HashMap<>();
        this.juego = new Juego();
    }
    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }
    @Override
    public String crearJuego(String id, int N_Equipos, int P_personas) throws JuegoYaExisteException {
            String estado = null;
            if (listaJuegos.contains(id)){
                logger.error("Ya hay un juego en marcha con esta id: " + id);
                throw new JuegoYaExisteException();
            }
            else {
                listaJuegos.add(id);
                this.juego.crearEquipos(N_Equipos,P_personas);
                logger.info("Juego creado con " + N_Equipos+ " equipos de "+P_personas+" personas ");
                this.juego.setEstado(Estado.INICIADO_EN_PREPARACION);
                estado = "INICIADO_EN_PREPARACION";

            }
            return estado;

        //return id;
    }
    @Override
    public void addUsuario(String id, String nombre, String apellido, int vida, double DSA_coins) throws UsuarioYaExisteException {
        logger.info("Comprobamos si el usuario con id: "+id+" existe");
        if(listaUsuarios.contains(id)) {
            logger.error("Este usuario ya existe");
            throw new UsuarioYaExisteException();

        }
        else {
            logger.info("El usuario no existe, lo añadimos con la id: "+id);
            Usuario usuario = new Usuario(id,nombre,apellido,vida,DSA_coins);
            listaUsuarios.addLast(id);
        }
    }
    @Override
    public void addProducto(String id, String descripcion, double precio) throws ProductoYaExisteException {
        logger.info("Comprobamos si el producto con id: "+id+" existe");
        if(listaProductos.contains(id)) {
            logger.error("Este producto ya existe");
            throw new ProductoYaExisteException();
        }
        else {
            logger.info("El producto no existe, lo añadimos con la id: "+id);
            Producto producto = new Producto(id,descripcion,precio);
            listaProductos.addLast(id);
        }
    }

    @Override
    public void comprarProducto(String idProducto, String idUsuario) throws ProductoNoExisteException, DineroInsuficienteException {
        logger.info("Comprobamos si el producto con id "+idProducto+" y el usuario con id: "+idUsuario+" existen");
        if(Producto.Tienda.containsKey(idProducto)){
            Usuario usuario = Usuario.MapaUsuarios.get(idUsuario);
            Producto producto = Producto.Tienda.get(idProducto);
            if (usuario.getDSA_coins() > producto.getPrecio()) {
                usuario.getInventario().add(producto);
                double monedero = usuario.getDSA_coins();
                usuario.setDSA_coins(monedero - producto.getPrecio());
                logger.info("El jugador con id "+idUsuario+ " ha comprado el producto con id "+idProducto);
            }
            else {
                logger.info("El usuario no tiene suficientes DSA_coins");
                throw new DineroInsuficienteException();
            }
        }
        else{
            logger.info("El producto no existe");
            throw new ProductoNoExisteException();
        }
    }
    @Override
    public String iniciarPartida(String idPartida, String idUsuario) throws UsuarioActivoException, JuegoNoExisteException {
        String estado = null;
        boolean encontrado = false;
        if(listaJuegos.contains(idPartida)) {
            logger.info("Ya existe una partida con identificador: " + idPartida);
            if (idPartida.equals(Juegos.get((idUsuario)))) {
                logger.error("El usuario ya esta en una partida");
                throw new UsuarioActivoException();
            }
            else {
                encontrado = true;
            }
        }
        else {
            logger.error("No existe el juego o el usuario");
            throw new JuegoNoExisteException();
        }
        if (encontrado){
            List<Equipo> equipos = this.juego.getEquipos();
            Usuario usuario = Usuario.MapaUsuarios.get(idUsuario);
            for (int i = 1; i < equipos.size(); i++) {
                if (equipos.get(i - 1).getUsuarios().size() == 0) {
                    equipos.get(i - 1).addUsuario(usuario);
                } else if (equipos.get(i - 1).getUsuarios().size() == equipos.get(i).getUsuarios().size()) {
                    equipos.get(i - 1).addUsuario(usuario);
                }
            }
            logger.info("Se ha iniciado partida correctamente");
            Juegos.put(idPartida,idUsuario);
            estado = "INICIADO_EN_FUNCIONAMIENTO";
            this.juego.setEstado(Estado.INICIADO_EN_FUNCIONAMIENTO);
        }
        return estado;
    }
    public Estado consultarEstado(){
        logger.info("Estado del juego : " + this.juego.getEstado());
        return this.juego.getEstado();

    }
    @Override
    public int consultarVidaUsuario(String idUsuario) throws UsuarioNoExisteException {
        int vida;
        if (!listaUsuarios.contains(idUsuario)) {
            logger.error("El usuario no existe o no está en partida");
            throw new UsuarioNoExisteException();

        } else {
            logger.info("El usuario con id: " + idUsuario + " existe");
            Usuario usuario = Usuario.MapaUsuarios.get(idUsuario);
            vida = usuario.getVida();
            logger.info("La vida del usuario es: "+ vida);
            return vida;
        }

    }
    @Override
    public int consultarVidaEquipo(int N_Equipo) throws EquipoNoExisteException {
        int resultado = 0;
        if (N_Equipo < this.juego.getEquipos().size() - 1) {
            Equipo equipo = this.juego.getEquipos().get(N_Equipo);
            resultado = equipo.getVida();
            logger.info(resultado);
        }
        else{
                logger.error("Este equipo no existe");
                resultado = -1;
                throw new EquipoNoExisteException();
            }
            return resultado;
        }
    @Override
    public Integer actualizarVida(String idUsuario, int daño) throws UsuarioNoExisteException {
        int vidaact;
        if (Usuario.MapaUsuarios.containsKey(idUsuario)){
            Usuario usuario = Usuario.MapaUsuarios.get(idUsuario);
            int vida = usuario.getVida();
            usuario.setVida(vida-daño);
            vidaact = usuario.getVida();
            logger.info("El usuario tiene una vida de: "+vida);
            logger.info("El usuario ha sufrido un daño de: "+daño);
            logger.info("El usuario ahora tiene una vida de: "+vidaact);
        } else {
            logger.warn("Usuario con id: "+idUsuario+ " no existe");
            throw new UsuarioNoExisteException();
        }
        return vidaact;
    }
    @Override
    public String finalizarJuego(String idJuego) throws JuegoNoExisteException {
        String estado = null;
        if (listaJuegos.contains(idJuego)){
            listaJuegos.remove(idJuego);
            estado = "FINALIZADO";
            this.juego.setEstado(Estado.FINALIZADO);
            logger.info("Se ha finalizado la partida");
                }
        else{
            logger.error("No existe el juego");
            throw new JuegoNoExisteException();
        }
        return estado;
    }
    @Override
    public int numUsuarios() {
        return this.listaUsuarios.size();
    }

    @Override
    public int numProductos() {
        return this.listaProductos.size();
    }

    @Override
    public int size() {
        int ret = this.usuario.size();
        logger.info("size " + ret);
        return ret;
    }
}