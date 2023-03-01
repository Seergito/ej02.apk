package com.tesdai.pesajes;

import java.io.Serializable;

public class Peso implements Serializable {

    int peso;
    String fecha;

    public Peso(int peso,String fecha){
        this.peso=peso;
        this.fecha=fecha;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
