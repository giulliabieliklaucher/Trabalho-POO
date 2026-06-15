package Excecoes;

public class AcaoInvalidaException extends RuntimeException{
    public  AcaoInvalidaException(int escolha){
        super("Acao invalida");
    }
}
