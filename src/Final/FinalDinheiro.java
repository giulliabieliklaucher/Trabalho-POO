// FinalDinheiro.java
package Final;

import Estado.Estados;

public class FinalDinheiro extends FimDeJogo {
    private Estados estado;

    public FinalDinheiro(Estados estado) {
        this.estado = estado;
    }

    @Override
    public String descricao() {
        if (estado.getDinheiro() <= 0)
            return "Os cofres do reino estão vazios. Sem dinheiro para pagar soldados ou comprar alimentos, o reino entrou em colapso total.";
        else if (estado.getDinheiro() >= estado.getMax())
            return "A riqueza extrema corrompeu todos ao seu redor. Nobres ambiciosos se voltaram contra você para tomar o trono e o tesouro.";
        return "";
    }
}