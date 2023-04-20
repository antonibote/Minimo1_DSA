package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Usuario {
    String id;
    String nombre;
    String apellido;
    public Usuario(String id, String nombre, String apellido){
        this.id = id;
        this.nombre=nombre;
        this.apellido=apellido;

    }
    public Usuario(){this.id = RandomUtils.getId();};
    public String getId(){return this.id;};
    public void setId(String id){this.id=id;};
    public String getNombre(){return this.nombre;};
    public void setNombre(String nombre){this.nombre=nombre;};
    public String getApellido(){return this.apellido;};
    public void setApellido(String apellido){this.apellido=apellido;};

}
