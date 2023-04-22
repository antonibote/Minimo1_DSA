package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Estado;

public interface GameManager {

    String crearJuego(String id, int N_Equipos, int P_personas) throws JuegoYaExisteException;
    void addUsuario(String id, String nombre, String apellido, int vida, double DSA_coins) throws UsuarioYaExisteException;

    void addProducto(String idProducto, String descripcion, double precio) throws ProductoYaExisteException;
    void comprarProducto(String idProducto, String idUsuario) throws  ProductoNoExisteException, DineroInsuficienteException;
    String iniciarPartida(String idJuego, String idUsuario) throws UsuarioActivoException, JuegoActivoException, JuegoNoExisteException;
    Estado consultarEstado();
    int consultarVidaUsuario(String idUsuario) throws UsuarioNoExisteException;

    int consultarVidaEquipo(int n) throws EquipoNoExisteException;

    Integer actualizarVida(String idUsuario, int daño) throws UsuarioNoExisteException;

    String finalizarJuego(String idJuego) throws JuegoNoExisteException;


    int numUsuarios();

    int numProductos();

    int size();
}
