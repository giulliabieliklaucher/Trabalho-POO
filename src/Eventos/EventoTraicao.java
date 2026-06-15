package Eventos;

import Estado.Estados;
import Excecoes.AcaoInvalidaException;

import java.util.Random;

public class EventoTraicao implements Eventos {
    @Override
    public String getDescricao() {
        return "Um soldado sussura que seu capitao planeja uma rebeliao";
    }

    @Override
    public String getOpA() {
        return "Executar o suspeito publicamente " + "(+exercito, -populacao, -igreja)";
    }

    @Override
    public String getOpB() {
        return "Ignorar, pode ser mentira " + "(-exercito)";
    }

    @Override
    public void consequencias(Estados estado, int escolha) {
        Random random = new Random();
        int variacao = 10 + random.nextInt(11);

        if(escolha == 1){
            estado.setExercito(estado.getExercito() + variacao);
            estado.setPopulacao(estado.getPopulacao() - variacao);
            estado.setIgreja(estado.getIgreja() + variacao);
        } else if(escolha == 2){
            estado.setExercito(estado.getExercito() - variacao);
        } else {
            throw new AcaoInvalidaException(escolha);
        }
    }
}
