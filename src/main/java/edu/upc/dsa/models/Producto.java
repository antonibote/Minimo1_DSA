package edu.upc.dsa.models;

import java.util.HashMap;
import java.util.Map;

public class Producto {
    String idProducto;
    String descripcion;
    double precio;
    public static Map<String, Producto> Tienda = new HashMap<>();
    public Producto(String idProducto, String descripcion, double precio){
        this.idProducto = idProducto;
        this.descripcion=descripcion;
        this.precio=precio;
        Tienda.put(this.idProducto, this);
    }
    public Producto(){};
    public String getId(){return this.idProducto;};
    public void setId(String idProducto){this.idProducto=idProducto;};
    public String getDescripcion(){return this.descripcion;};
    public void setDescripcion(String descripcion){this.descripcion=descripcion;};
    public double getPrecio(){return this.precio;};
    public void setPrecio(double precio){this.precio=precio;};
}
