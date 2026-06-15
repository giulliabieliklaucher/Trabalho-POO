package Excecoes;

public class PoliticaNaoEncontradaException extends RuntimeException {
    public PoliticaNaoEncontradaException(String nome) {
        super("Nenhuma politica encontrada com o nome: " + nome);
    }
}
