package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Partida {
    String estado;
    String idPartida;
    String idUsuario;
    ArrayList<Juego> Partidas = new ArrayList<Juego>();
    HashMap<String,String> usuarioMonedero= new HashMap<>();
    public Partida(String idPartida, String idUsuario){
        this.idPartida = idPartida;
        this.idUsuario = idUsuario;
    }
    public Partida(){};
    public void Juego() {this.estado = "NO_INICIADO";}
    public String getIdJuego(){return this.idPartida;}

    public void setIdJuego(String id){this.idPartida = id;}
    public String getEstado(){return  this.estado;}

    public void setEstado(String estado){this.estado = estado;}

    public String getIdUsuario(){return  this.idUsuario;}

    public void setIdUsuario(String id){this.idUsuario = id;}
    public ArrayList<Juego> getPartidas(){return Partidas;}

    public HashMap<String,String> getMonedero(){
        return usuarioMonedero;
    }

}
