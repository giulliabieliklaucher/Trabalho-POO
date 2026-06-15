package Jogo;

import Final.FimDeJogo;
import Final.FinalDinheiro;
import Final.FinalMilitar;
import Final.FinalPopulacao;
import Final.FinalReligiao;

import Estado.Estados;
import Eventos.Eventos;
import reino.politica.Politica;

import java.util.ArrayList;
import java.util.List;



public class RegistroJogo {



    // CLASSE INTERNA PARA CADA TURNO
    public static class RegistroTurno {
        private final int turno;
        private final String eventoDescricao;
        private final int escolhaEvento;
        private final String politicaAplicada;
        private final int dinheiroAntes, populacaoAntes, exercitoAntes, igrejaAntes;
        private final int dinheiroDepois, populacaoDepois, exercitoDepois, igrejaDepois;

        public RegistroTurno(int turno, String eventoDescricao, int escolhaEvento,
                             String politicaAplicada,
                             int dinheiroAntes, int populacaoAntes, int exercitoAntes, int igrejaAntes,
                             int dinheiroDepois, int populacaoDepois, int exercitoDepois, int igrejaDepois) {
            this.turno = turno;
            this.eventoDescricao = eventoDescricao;
            this.escolhaEvento = escolhaEvento;
            this.politicaAplicada = politicaAplicada;
            this.dinheiroAntes = dinheiroAntes;
            this.populacaoAntes = populacaoAntes;
            this.exercitoAntes = exercitoAntes;
            this.igrejaAntes = igrejaAntes;
            this.dinheiroDepois = dinheiroDepois;
            this.populacaoDepois = populacaoDepois;
            this.exercitoDepois = exercitoDepois;
            this.igrejaDepois = igrejaDepois;
        }

        public int getTurno()              { return turno; }
        public String getEventoDescricao() { return eventoDescricao; }
        public int getEscolhaEvento()      { return escolhaEvento; }
        public String getPoliticaAplicada(){ return politicaAplicada; }
        public int getDinheiroAntes()      { return dinheiroAntes; }
        public int getPopulacaoAntes()     { return populacaoAntes; }
        public int getExercitoAntes()      { return exercitoAntes; }
        public int getIgrejaAntes()        { return igrejaAntes; }
        public int getDinheiroDepois()     { return dinheiroDepois; }
        public int getPopulacaoDepois()    { return populacaoDepois; }
        public int getExercitoDepois()     { return exercitoDepois; }
        public int getIgrejaDepois()       { return igrejaDepois; }
    }

    // ========== REGISTRO PRINCIPAL ==========
    private final String nomeRei;
    private final List<RegistroTurno> historico;
    private int turnoFinal;
    private String motivoDerrota;

    // Estado antes do turno (salvo temporariamente)
    private int dinheiroAntes, populacaoAntes, exercitoAntes, igrejaAntes;
    private String eventoAtual;
    private int escolhaEventoAtual;

    public RegistroJogo(String nomeRei) {
        this.nomeRei = nomeRei;
        this.historico = new ArrayList<>();
        this.turnoFinal = 0;
        this.motivoDerrota = "";
    }

    // ========== SALVAR ESTADO ANTES DO TURNO ==========
    public void salvarEstadoAntes(Estados estado) {
        this.dinheiroAntes   = estado.getDinheiro();
        this.populacaoAntes  = estado.getPopulacao();
        this.exercitoAntes   = estado.getExercito();
        this.igrejaAntes     = estado.getIgreja();
    }

    // ========== SALVAR EVENTO DO TURNO ==========
    public void salvarEvento(Eventos evento, int escolha) {
        this.eventoAtual       = evento.getDescricao();
        this.escolhaEventoAtual = escolha;
    }

    // ========== REGISTRAR TURNO COMPLETO ==========
    public void registrarTurno(int turno, Politica politica, Estados estadoDepois) {
        historico.add(new RegistroTurno(
                turno,
                eventoAtual,
                escolhaEventoAtual,
                politica.getTitulo(),
                dinheiroAntes, populacaoAntes, exercitoAntes, igrejaAntes,
                estadoDepois.getDinheiro(), estadoDepois.getPopulacao(),
                estadoDepois.getExercito(), estadoDepois.getIgreja()
        ));
    }

    // ========== REGISTRAR FIM DE JOGO ==========
    public void registrarFimDeJogo(int turno, Estados estado) {
        this.turnoFinal = turno;

        if (estado.getDinheiro() <= 0)       motivoDerrota = " O reino faliu!";
        else if (estado.getDinheiro() >= estado.getMax()) motivoDerrota = " A riqueza corrompeu o reino!";
        else if (estado.getPopulacao() <= 0) motivoDerrota = "O povo foi dizimado!";
        else if (estado.getPopulacao() >= estado.getMax()) motivoDerrota = " O povo tomou o poder!";
        else if (estado.getExercito() <= 0)  motivoDerrota = " O exército foi destruído!";
        else if (estado.getExercito() >= estado.getMax()) motivoDerrota = " Os generais tomaram o trono!";
        else if (estado.getIgreja() <= 0)    motivoDerrota = "A fé foi perdida!";
        else if (estado.getIgreja() >= estado.getMax())   motivoDerrota = " A igreja assumiu o controle!";
    }

    // ========== EXIBIR HISTÓRICO ==========
    public void exibirHistorico() {
        System.out.println("\n========================================");
        System.out.println("  HISTÓRICO DO REINO — Rei " + nomeRei);
        System.out.println("========================================");

        for (RegistroTurno r : historico) {
            System.out.println("\n--- Turno " + r.getTurno() + " ---");
            System.out.println("⚡ Evento:   " + r.getEventoDescricao());
            System.out.println("   Escolha:  Opção " + r.getEscolhaEvento());
            System.out.println("Política: " + r.getPoliticaAplicada());
            System.out.println("   Antes  → Dinheiro: " + r.getDinheiroAntes() +
                    " | Povo: " + r.getPopulacaoAntes() +
                    " | Exército: " + r.getExercitoAntes() +
                    " | Igreja: " + r.getIgrejaAntes());
            System.out.println("   Depois → Dinheiro: " + r.getDinheiroDepois() +
                    " | Povo: " + r.getPopulacaoDepois() +
                    " | Exército: " + r.getExercitoDepois() +
                    " | Igreja: " + r.getIgrejaDepois());
        }
    }

    // ========== EXIBIR PONTUAÇÃO FINAL ==========
    public void exibirPontuacaoFinal() {
        System.out.println("\n========================================");
        System.out.println("            FIM DE JOGO");
        System.out.println("========================================");
        System.out.println("Rei:         " + nomeRei);
        System.out.println("Durou:       " + turnoFinal + " turnos");
        System.out.println("Motivo:      " + motivoDerrota);
        System.out.println("Pontuação:   " + calcularPontuacao() + " pts");
        System.out.println("========================================");
    }

    // ========== CALCULAR PONTUAÇÃO ==========
    private int calcularPontuacao() {
        // 100 pontos por turno sobrevivido
        return turnoFinal * 100;
    }

    public List<RegistroTurno> getHistorico() { return historico; }
    public int getTurnoFinal()                { return turnoFinal; }
    public String getMotivoDerrota()          { return motivoDerrota; }
    public void salvarEmArquivo() {
        String nomeArquivo = "registro_" + nomeRei + "_turno" + turnoFinal + ".txt";

        try (java.io.PrintWriter writer = new java.io.PrintWriter(
                new java.io.FileWriter(nomeArquivo))) {

            writer.println("========================================");
            writer.println("  HISTÓRICO DO REINO — Rei " + nomeRei);
            writer.println("========================================");

            for (RegistroTurno r : historico) {
                writer.println("\n--- Turno " + r.getTurno() + " ---");
                writer.println("Evento:   " + r.getEventoDescricao());
                writer.println("Escolha:  Opção " + r.getEscolhaEvento());
                writer.println("Política: " + r.getPoliticaAplicada());
                writer.println("Antes  → Dinheiro: " + r.getDinheiroAntes() +
                        " | Povo: "            + r.getPopulacaoAntes() +
                        " | Exército: "        + r.getExercitoAntes() +
                        " | Igreja: "          + r.getIgrejaAntes());
                writer.println("Depois → Dinheiro: " + r.getDinheiroDepois() +
                        " | Povo: "            + r.getPopulacaoDepois() +
                        " | Exército: "        + r.getExercitoDepois() +
                        " | Igreja: "          + r.getIgrejaDepois());
            }

            writer.println("\n========================================");
            writer.println("            FIM DE JOGO");
            writer.println("========================================");
            writer.println("Rei:       " + nomeRei);
            writer.println("Durou:     " + turnoFinal + " turnos");
            writer.println("Motivo:    " + motivoDerrota);
            writer.println("Pontuação: " + calcularPontuacao() + " pts");
            writer.println("========================================");

            System.out.println("\n✓ Registro salvo em: " + nomeArquivo);

        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
