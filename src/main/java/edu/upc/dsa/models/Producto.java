package edu.upc.dsa.models;

public class Producto {
    String id;
    String descripcion;
    double precio;
    public Producto(String id, String descripcion, double precio){
        this.id = id;
        this.descripcion=descripcion;
        this.precio=precio;
    }
    public Producto(){};
    public String getId(){return this.id;};
    public void setId(String id){this.id=id;};
    public String getDescripcion(){return this.descripcion;};
    public void setDescripcion(String descripcion){this.descripcion=descripcion;};
    public double getPrecio(){return this.precio;};
    public void setPrecio(double precio){this.precio=precio;};
}
