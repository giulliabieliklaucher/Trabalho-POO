package Eventos;

import Estado.Estados;

import java.util.*;



public class GerenciarEventos {
    private List<Eventos> eventos;
    private Random random = new Random();


    public GerenciarEventos(){
        eventos = new ArrayList<>(List.of(
                new EventoColheita(), new EventoPraga(),
                new EventoFestival(), new EventoTraicao(),
                new EventosSimples()
        ));
    }

    public Eventos sortearEventos(){
        return eventos.get(random.nextInt(eventos.size()));
    }
}
