import Jogador.Rei;
import Eventos.GerenciarEventos;
import reino.politica.GerenciadorPoliticas;
import Jogo.RegistroJogo;
import Jogo.Turno;

import java.util.Scanner;

public class Main {

    private static final int TURNOS_POR_POLITICA = 3; // política a cada 3 turnos

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("========================================");
        System.out.println("        BEM-VINDO AO REINO!");
        System.out.println("========================================");
        System.out.print("Digite o nome do seu Rei: ");
        String nome = scanner.nextLine().trim();


        Rei rei                             = new Rei(nome);
        GerenciarEventos gerenciarEventos   = new GerenciarEventos();
        GerenciadorPoliticas gerenciadorPoliticas = new GerenciadorPoliticas();
        RegistroJogo registro               = new RegistroJogo(nome);

        Turno turno = new Turno(rei, gerenciarEventos, gerenciadorPoliticas, registro, scanner);

        System.out.println("\nQue seu reinado seja longo, " + nome + "!");
        System.out.println("Sobreviva mantendo o equilíbrio entre");
        System.out.println("Dinheiro, Povo, Exército e Igreja.");
        System.out.println("Se qualquer um chegar a 0 ou 300... é o fim.");
        System.out.println("\nPressione ENTER para começar...");
        scanner.nextLine();


        while (!rei.getEstado().fimDeJogo()) {
            // Oferece política a cada N turnos
            boolean oferecerPolitica = (rei.getTurno() % TURNOS_POR_POLITICA == 0);
            turno.executar(oferecerPolitica);
        }


        registro.registrarFimDeJogo(rei.getTurno(), rei.getEstado());
        registro.exibirHistorico();
        registro.exibirPontuacaoFinal();
        registro.salvarEmArquivo();

        scanner.close();
    }
}