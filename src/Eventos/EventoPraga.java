package Eventos;

import Estado.Estados;
import Excecoes.AcaoInvalidaException;

import java.util.Random;

public class EventoPraga implements Eventos {
    @Override
    public String getDescricao() {
        return "Uma praga esta se espalhando pelos campos, os camponeses imploram por ajuda";
    }

    @Override
    public String getOpA() {
        return "Tentar combater a praga " + "(-dinheiro, +populacao)";
    }

    @Override
    public String getOpB() {
        return "Pedir a bencao da igreja para afastar o mal " + "(+igreja, -populacao, -dinheiro)";
    }

    @Override
    public void consequencias(Estados estado, int escolha) {
        Random random = new Random();
        int variacao = 10 + random.nextInt(11);

        if(escolha == 1){
            estado.setDinheiro(estado.getDinheiro() - variacao);
            estado.setPopulacao(estado.getPopulacao() + variacao);
        } else if(escolha == 2){
            estado.setIgreja(estado.getIgreja() + variacao);
            estado.setPopulacao(estado.getPopulacao() - variacao);
            estado.setDinheiro(estado.getDinheiro() - variacao);
        } else {
            throw new AcaoInvalidaException(escolha);
        }

    }
}
