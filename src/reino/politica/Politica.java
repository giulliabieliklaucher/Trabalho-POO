package reino.politica;

public class Politica {
    private final String titulo;
    private final String descricao;
    private final int dinheiro;
    private final int populacao;
    private final int exercito;
    private final int igreja;

    public Politica(String titulo, String descricao, int dinheiro,
                    int populacao, int exercito, int igreja) {
        if(titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Titulo nao pode ser vazio");
        }

        if(descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descricao nao pode ser vazia");
        }

        this.titulo = titulo;
        this.descricao = descricao;
        this.dinheiro = dinheiro;
        this.populacao = populacao;
        this.exercito = exercito;
        this.igreja = igreja;
    }

    public String getTitulo()    { return titulo; }
    public String getDescricao() { return descricao; }
    public int getDinheiro()     { return dinheiro; }
    public int getPopulacao()    { return populacao; }
    public int getExercito()     { return exercito; }
    public int getIgreja()       { return igreja; }
}