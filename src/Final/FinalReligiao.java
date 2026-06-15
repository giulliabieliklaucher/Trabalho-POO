package Final;

import Estado.Estados;

public class FinalReligiao extends FimDeJogo{
    private Estados estado;

    @Override
    public String descricao() {
        if(estado.getIgreja() <= 0){
            return "A fé acabou. Os templos foram abandonados. A população perdeu o medo do divino, você foi deposto" +
                    " por heresia";
        } else if (estado.getIgreja() >= estado.getMax()){
            return "A igreja assumiu o controle total do reino. Você não possui mais poder nenhum";
        }
        return "";
    }
}
