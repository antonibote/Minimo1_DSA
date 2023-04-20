package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Partida {
    double DSA_coins = 0;
    int vida = 100;
    String estado=null;
    String idJuego;
    String idUsuario;
    ArrayList<Juego> Partidas = new ArrayList<Juego>();
    HashMap<String,String> usuarioMonedero= new HashMap<>();
    public Partida(String idJuego, String idUser){
        this.idJuego = idJuego;
        this.idUsuario = idUser;
    }
    public Partida(){};
    public String getIdJuego(){return this.idJuego;}

    public void setIdJuego(String id){this.idJuego = id;}
    public String getEstado(){return  this.estado;}

    public void setEstado(String estado){this.estado = estado;}

    public String getIdUsuario(){return  this.idUsuario;}

    public void setIdUsuario(String id){this.idUsuario = id;}

    public int getVida(){return this.vida;}

    public void setVida(int vida){this.vida += vida;}

    public double getDSA_coins(){return this.DSA_coins;}

    public void setNivel(double nivel){this.DSA_coins += 0;}

    public ArrayList<Juego> getPartidas(){return Partidas;}

    public HashMap<String,String> getMonedero(){
        return usuarioMonedero;
    }

}
