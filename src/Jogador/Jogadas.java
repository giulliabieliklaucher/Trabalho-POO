package Jogador;

import Estado.Estados;
import Eventos.Eventos;
import Eventos.GerenciarEventos;
import Jogo.RegistroJogo;
import reino.politica.GerenciadorPoliticas;
import reino.politica.Politica;

import java.util.List;
import java.util.Scanner;

public class Jogadas {
    private final Rei rei;
    private final GerenciadorPoliticas gerenciadorPoliticas;
    private final GerenciarEventos gerenciadorEventos;
    private final RegistroJogo registro;
    private final Scanner scanner;

    public Jogadas(Rei rei) {
        this.rei = rei;
        this.gerenciadorPoliticas = new GerenciadorPoliticas();
        this.gerenciadorEventos = new GerenciarEventos();
        this.registro = new RegistroJogo(rei.getNome());
        this.scanner = new Scanner(System.in);
    }


    public boolean executarTurno() {
        System.out.println("\n==============================");
        System.out.println("  TURNO " + rei.getTurno() + " — Rei " + rei.getNome());
        System.out.println("==============================");
        mostrarEstado();


        registro.salvarEstadoAntes(rei.getEstado());


        Eventos evento = resolverEvento();


        if (rei.getEstado().fimDeJogo()) {
            encerrarJogo();
            return false;
        }


        Politica politica = escolherPolitica();


        registro.registrarTurno(rei.getTurno(), politica, rei.getEstado());


        if (rei.getEstado().fimDeJogo()) {
            encerrarJogo();
            return false;
        }

        rei.proxTurno();
        return true;
    }


    private Eventos resolverEvento() {
        Eventos evento = gerenciadorEventos.sortearEventos();

        System.out.println("\n⚡ EVENTO DO TURNO:");
        System.out.println(evento.getDescricao());
        System.out.println("\n[1] " + evento.getOpA());
        System.out.println("[2] " + evento.getOpB());

        int escolha = lerEscolha(1, 2);
        evento.consequencias(rei.getEstado(), escolha);


        registro.salvarEvento(evento, escolha);

        System.out.println("✓ Decisão tomada.");
        mostrarEstado();

        return evento;
    }


    private Politica escolherPolitica() {
        List<Politica> lista = gerenciadorPoliticas.getPoliticasDisponiveis();

        System.out.println("\n📜 ESCOLHA UMA POLÍTICA:");
        for (int i = 0; i < lista.size(); i++) {
            Politica p = lista.get(i);
            System.out.println("[" + i + "] " + p.getTitulo());
            System.out.println("     " + p.getDescricao());
            System.out.println("     Dinheiro: " + p.getDinheiro() +
                    " | Povo: "       + p.getPopulacao() +
                    " | Exército: "   + p.getExercito() +
                    " | Igreja: "     + p.getIgreja());
        }

        int escolha = lerEscolha(0, lista.size() - 1);
        Politica politica = lista.get(escolha);
        rei.getEstado().aplicarPolitica(politica);

        System.out.println("✓ Política aplicada: " + politica.getTitulo());
        mostrarEstado();

        return politica;
    }


    private void encerrarJogo() {
        registro.registrarFimDeJogo(rei.getTurno(), rei.getEstado());
        registro.exibirHistorico();
        registro.exibirPontuacaoFinal();
    }


    public void mostrarEstado() {
        Estados e = rei.getEstado();
        System.out.println("\n--- Estado do Reino ---");
        System.out.println("Dinheiro: " + e.getDinheiro() + "/" + e.getMax());
        System.out.println("Povo:     " + e.getPopulacao() + "/" + e.getMax());
        System.out.println("⚔ Exército: " + e.getExercito() + "/" + e.getMax());
        System.out.println("✝ Igreja:   " + e.getIgreja() + "/" + e.getMax());
        System.out.println("-----------------------");
    }


    private int lerEscolha(int min, int max) {
        int escolha = -1;
        while (escolha < min || escolha > max) {
            System.out.print("Sua escolha (" + min + "-" + max + "): ");
            try {
                escolha = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        }
        return escolha;
    }

    public RegistroJogo getRegistro() {
        return registro;
    }
}