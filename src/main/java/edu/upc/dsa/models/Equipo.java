package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    int N_Equipo;
    int P_personas;
    List<Usuario> usuarios =new ArrayList<Usuario>();
    public Equipo(int N_Equipo, int P_personas){
        this.N_Equipo=N_Equipo;
        this.P_personas=P_personas;
    }
    public Equipo(){};
    public int getVida(){
        int vida =0;
        for(int i=0;i<usuarios.size(); i++){
            vida += usuarios.get(i).getVida();

        }
        return vida;
    }
    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }
    public void addUsuario(Usuario usuario){
            this.usuarios.add(usuario);
        }
    public int  getP_personas() {
        return P_personas;
    }

    public void setP_personas() {
        this.P_personas = P_personas;
    }
    /**
    @Override
    public String toString() {
        return "Número de jugadores en el equipo: " + P_personas +"]";
    }
    **/
}
