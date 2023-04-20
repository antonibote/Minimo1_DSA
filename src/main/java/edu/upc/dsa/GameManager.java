package edu.upc.dsa;

import edu.upc.dsa.models.Track;

import java.util.List;

public interface GameManager {

    String crearJuego(String id, int N_Equipos, int P_personas);
    void addUsuario(String idUsuario, String nombre, String apellido);
    void addProducto(String idProducto, String descripcion, double precio);
    void comprarProducto(String idProducto, String idUsuario);
    String iniciarPartida(String idJuego, String idUsuario);
    String consultarEstado(String idJuego);
    int consultarVida(String idUsuario);
    String finalizarJuego(String idJuego);


    int numUsuarios();

    int numProductos();
}
