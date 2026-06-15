package Eventos;

import Estado.Estados;

public interface Eventos {
    String getDescricao();
    String getOpA();
    String getOpB();

    void consequencias(Estados estado, int escolha);

}
/*colheita praga festival traicao*/