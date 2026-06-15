package Final;

import Estado.Estados;

public class FinalPopulacao extends FimDeJogo{
    Estados estado;

    public FinalPopulacao(Estados estado) {
        this.estado = estado;
    }

    @Override
    public String descricao() {
        if(estado.getPopulacao() <= 0){
            return "A fome e a opressão passaram dos limites. O povo marchou até o palácio com tochas e armas." +
                    " As portas foram derrubadas e a raiva da população selou seu destino na guilhotina";
        } else if(estado.getPopulacao() >= estado.getMax()){
            return "A população ficou tão empoderada que o governo se tornou obsoleto. As pessoas começaram a " +
                    " ignorar suas leis e impostos. Seu estado simplesmente deixou de existir";
        }
        return "";
    }
}
