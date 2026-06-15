package Eventos;

import Estado.Estados;
import Excecoes.AcaoInvalidaException;

import java.util.Random;

public class EventoColheita implements Eventos {
    @Override
    public String getDescricao() {
        return "A colheita foi um sucesso! O silo esta transbordando de graos";
    }

    @Override
    public String getOpA() {
        return "Distribuir a populacao " + "(+populacao, -dinheiro)";
    }

    @Override
    public String getOpB() {
        return "Vender o excedente " + "(-populacao, +dinheiro)";
    }

    @Override
    public void consequencias(Estados estado, int escolha) {
        //variacao nas consequencias
        Random random = new Random();
        int variacao = 10 + random.nextInt(11); //entre 10 e 20

        if(escolha == 1){
            estado.setPopulacao(estado.getPopulacao() + variacao);
            estado.setDinheiro(estado.getDinheiro() - variacao);
        }
        else if(escolha == 2){
            estado.setPopulacao(estado.getPopulacao() - variacao);
            estado.setDinheiro(estado.getDinheiro() + variacao);
        } else {
            throw new AcaoInvalidaException(escolha);
        }
    }
}
