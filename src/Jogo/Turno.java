package Jogo;

import Estado.Estados;
import Eventos.Eventos;
import Eventos.GerenciarEventos;
import Jogador.Rei;
import Jogo.RegistroJogo;
import reino.politica.GerenciadorPoliticas;
import reino.politica.Politica;

import java.util.List;
import java.util.Scanner;

public class Turno {
    private final Rei rei;
    private final GerenciarEventos gerenciadorEventos;
    private final GerenciadorPoliticas gerenciadorPoliticas;
    private final RegistroJogo registro;
    private final Scanner scanner;

    public Turno(Rei rei, GerenciarEventos gerenciadorEventos,
                 GerenciadorPoliticas gerenciadorPoliticas,
                 RegistroJogo registro, Scanner scanner) {
        this.rei = rei;
        this.gerenciadorEventos = gerenciadorEventos;
        this.gerenciadorPoliticas = gerenciadorPoliticas;
        this.registro = registro;
        this.scanner = scanner;
    }


    public void executar(boolean oferecerPolitica) {
        System.out.println("\n==============================");
        System.out.println("  TURNO " + rei.getTurno() + " — Rei " + rei.getNome());
        System.out.println("==============================");
        mostrarEstado();


        registro.salvarEstadoAntes(rei.getEstado());


        Eventos evento = resolverEvento();

        if (rei.getEstado().fimDeJogo()) return;


        Politica politica = null;
        if (oferecerPolitica) {
            politica = escolherPolitica();
        }


        if (politica != null) {
            registro.registrarTurno(rei.getTurno(), politica, rei.getEstado());
        }

        rei.proxTurno();
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

        System.out.println("\n📜 É hora de escolher uma política para o reino!");
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


    private void mostrarEstado() {
        Estados e = rei.getEstado();
        System.out.println("\n--- Estado do Reino ---");
        System.out.println("💰 Dinheiro: " + e.getDinheiro() + "/" + e.getMax());
        System.out.println("👥 Povo:     " + e.getPopulacao() + "/" + e.getMax());
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
}