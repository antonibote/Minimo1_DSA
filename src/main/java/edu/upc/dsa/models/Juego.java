package edu.upc.dsa.models;
import java.util.ArrayList;
import java.util.List;
public class Juego {
    String id;
    int N_Equipos;
    int P_personas;
    private List<Equipo> equipos = new ArrayList<>();
    private Estado estado;
    public Juego(String id, int N_Equipos, int P_personas){
        this.id = id;
        this.N_Equipos = N_Equipos;
        this.P_personas= P_personas;
    }
    public Juego() {this.estado = Estado.NO_INICIADO;}
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id=id;
    }
    public int getN_Equipos(){return this.N_Equipos;};
    public void setN_Equipos(int N_Equipos){this.N_Equipos=N_Equipos;};
    public int getP_personas(){return this.P_personas;};
    public void setP_personas(int P_personas){this.P_personas=P_personas;};
    public Estado getEstado() {
        return this.estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public List<Equipo> getEquipos() {
        return this.equipos;
    }
    public void crearEquipos(int N_Equipos, int P_personas) {
        for (int i = 0; i < N_Equipos; i++) {
            equipos.add(new Equipo(N_Equipos,P_personas));
        }
    }
    @Override
    public String toString() {
        return "Juego [Número de equipos= "+N_Equipos+", Número de personas= " + P_personas + "]";
    }

}
