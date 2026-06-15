package Jogador;

import Estado.Estados;
//import Final.FimDeJogo;

public class Rei {
    private String nome;
    private int turno;
    private Estados estado;

    public Rei(String nome){
        this.nome = nome;
        this.turno = 1;
        this.estado = new Estados();
    }

    public String getNome(){
        return nome;
    }

    public int getTurno(){
        return turno;
    }

    public void proxTurno(){
        turno++;
    }

    public Estados getEstado(){
        return estado;
    }

}
