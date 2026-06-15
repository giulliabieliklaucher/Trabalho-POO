package Final;

import Estado.Estados;

public class FinalMilitar extends FimDeJogo{
    private Estados estado;

    @Override
    public String descricao() {
        if(estado.getExercito() <= 0){
            return "Suas fronteiras ficaram indefesas. Uma nação vizinha percebeu sua fraqueza e marchou sobre a capital." +
                    " Sua bandeira foi queimada e o país anexado";
        } else if(estado.getExercito() >=estado.getMax()) {
            return "Seus generais decidiram que você é incompetente para governar. Você foi deposto e os militares" +
                    " assumiram o controle do reino";
        }
        return  "";
    }
}
