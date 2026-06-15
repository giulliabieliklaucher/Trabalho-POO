package Eventos;

import Estado.Estados;
import Excecoes.AcaoInvalidaException;

import java.util.Random;

public class EventosSimples implements Eventos{
    private Random random = new Random();
    private int cenario;

    public EventosSimples(){
        cenario = random.nextInt(3);
    }

    @Override
    public String getDescricao() {
        switch (cenario){
            case 0: return "Um bando de barbaros foram avistados na fronteira da cidade";
            case 1: return "O bisbo esta exigindo impostos extras para a construcao da nova catedral";
            case 2: return "O bisbo pede que envie soldados do exercito em uma cruzada para terras distantes";
            default: return "";
        }
    }

    @Override
    public String getOpA() {
        switch (cenario){
            case 0: return "Enviar o exercito para a fronteira";
            case 1: return "Pagar o bisbo";
            case 2: return "Enviar tropas";
            default: return "";
        }
    }

    @Override
    public String getOpB() {
        switch (cenario){
            case 0: return "Nao fazer nada";
            case 1: return "Recusar a exigencia";
            case 2: return "Recusar o pedido";
            default: return "";
        }
    }

    @Override
    public void consequencias(Estados estado, int escolha) {
        Random random = new Random();
        int variacao = 10 + random.nextInt(11);
        switch (cenario){
            case 0:
                if (escolha == 1){
                    estado.setExercito(estado.getExercito() - variacao);
                    estado.setPopulacao(estado.getPopulacao() + variacao);
                } else if (escolha == 2) {
                    estado.setExercito(estado.getExercito() + variacao);
                    estado.setPopulacao(estado.getPopulacao() - variacao);
                } else {
                    throw new AcaoInvalidaException(escolha);
                }
                break;

            case 1:
                if (escolha == 1){
                    estado.setIgreja(estado.getIgreja() + variacao);
                    estado.setDinheiro(estado.getDinheiro() - variacao);
                } else if(escolha == 2){
                    estado.setIgreja(estado.getIgreja() - variacao);
                    estado.setDinheiro(estado.getDinheiro() + variacao);
                } else {
                    throw new AcaoInvalidaException(escolha);
                }
                break;

            case 2:
                if (escolha == 1){
                    estado.setExercito(estado.getExercito() - variacao);
                    estado.setIgreja(estado.getIgreja() +variacao);
                } else if(escolha == 2) {
                    estado.setExercito(estado.getExercito() + variacao);
                    estado.setIgreja(estado.getIgreja() - variacao);
                } else {
                    throw new AcaoInvalidaException(escolha);
                }
                break;

            default:
        }
    }
}
