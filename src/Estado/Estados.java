package Estado;

import reino.politica.Politica;

public class Estados {
    private int igreja = 100;
    private int exercito = 100;
    private int populacao = 100;
    private int dinheiro = 100;

    private final int max = 350;
    private final int min = 0;

    public void setIgreja(int igreja)       { this.igreja = igreja; }
    public int getIgreja()                  { return igreja; }

    public void setExercito(int exercito)   { this.exercito = exercito; }
    public int getExercito()                { return exercito; }

    public void setPopulacao(int populacao) { this.populacao = populacao; }
    public int getPopulacao()               { return populacao; }

    public void setDinheiro(int dinheiro)   { this.dinheiro = dinheiro; }
    public int getDinheiro()                { return dinheiro; }

    public int getMax() { return max; }

    public void aplicarPolitica(Politica p) {
        this.dinheiro  = Math.min(max, Math.max(min, this.dinheiro  + p.getDinheiro()));
        this.populacao = Math.min(max, Math.max(min, this.populacao + p.getPopulacao()));
        this.exercito  = Math.min(max, Math.max(min, this.exercito  + p.getExercito()));
        this.igreja    = Math.min(max, Math.max(min, this.igreja    + p.getIgreja()));
    }

    public boolean fimDeJogo() {
        return igreja <= 0 || igreja >= max ||
                exercito <= 0 || exercito >= max ||
                populacao <= 0 || populacao >= max ||
                dinheiro <= 0 || dinheiro >= max;
    }
}