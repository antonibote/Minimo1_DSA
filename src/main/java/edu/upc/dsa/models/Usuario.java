package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario {
    public static Map<String, Usuario> MapaUsuarios = new HashMap<>();
    private List<Producto> Inventario = new ArrayList<>();
    String id;
    String nombre;
    String apellido;
    int vida;
    double DSA_coins;
    public Usuario(String id, String nombre, String apellido, int vida, double DSA_coins){
        this.id = id;
        this.nombre=nombre;
        this.apellido=apellido;
        this.vida = 100;
        this.DSA_coins = 25;
        MapaUsuarios.put(this.id, this);
    }
    public Usuario(){};
    public String getidUsuario(){return id;};
    public void setidUsuario(String id){this.id=id;};
    public String getNombre(){return this.nombre;};
    public void setNombre(String nombre){this.nombre=nombre;};
    public String getApellido(){return this.apellido;};
    public void setApellido(String apellido){this.apellido=apellido;};
    public int getVida(){return this.vida;}
    public void setVida(int vida){this.vida = vida;}
    public double getDSA_coins(){return this.DSA_coins;}
    public void setDSA_coins(double DSA_coins){this.DSA_coins += DSA_coins;}
    public List<Producto> getInventario() {
        return this.Inventario;
    }

}
