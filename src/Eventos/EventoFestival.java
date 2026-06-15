package Eventos;

import Estado.Estados;
import Excecoes.AcaoInvalidaException;

import java.util.Random;

public class EventoFestival implements Eventos {
    @Override
    public String getDescricao() {
        return "Um festival esta sendo feito pela populacao na cidade ";
    }

    @Override
    public String getOpA() {
        return "Financiar as festividades " + "(+populacao, +igreja, -dinheiro)";
    }

    @Override
    public String getOpB() {
        return "Nao se envolver " + "(-populacao, -igreja, +dinheiro)";
    }

    @Override
    public void consequencias(Estados estado, int escolha) {
        Random random = new Random();
        int variacao = 10 + random.nextInt(11);

        if(escolha == 1){
            estado.setPopulacao(estado.getPopulacao() + variacao);
            estado.setIgreja(estado.getIgreja() + variacao);
            estado.setDinheiro(estado.getDinheiro() - variacao);
        }
        else if(escolha == 2){
            estado.setPopulacao(estado.getPopulacao() - variacao);
            estado.setIgreja(estado.getIgreja() - variacao);
            estado.setDinheiro(estado.getDinheiro() + variacao);
        } else {
            throw new AcaoInvalidaException(escolha);
        }
    }
}
