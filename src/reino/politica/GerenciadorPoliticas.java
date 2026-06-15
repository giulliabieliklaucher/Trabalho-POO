package reino.politica;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorPoliticas {
    private final List<Politica> politicasDisponiveis;

    public GerenciadorPoliticas() {
        this.politicasDisponiveis = new ArrayList<>();
        carregarPoliticas();
    }


    private void carregarPoliticas() {
        // (titulo, descricao, dinheiro, populacao, exercito, igreja)

        // === IMPOSTOS ===
        politicasDisponiveis.add(new Politica(
                "Imposto de Guerra",
                "Taxa extra sobre comerciantes para financiar o exército.",
                -10, 0, +15, -5
        ));
        politicasDisponiveis.add(new Politica(
                "Isenção dos Pobres",
                "Reduz impostos para os mais pobres. Povo mais feliz, menos renda.",
                -15, +10, 0, +5
        ));
        politicasDisponiveis.add(new Politica(
                "Taxa sobre Nóbres",
                "Cobra mais dos nobres. Povo feliz, risco de conspiração.",
                +10, +8, 0, -5
        ));

        // === MILITARES ===
        politicasDisponiveis.add(new Politica(
                "Serviço Militar Obrigatório",
                "Todos os jovens servem ao exército por 2 anos.",
                -10, -8, +20, 0
        ));
        politicasDisponiveis.add(new Politica(
                "Guarda Real Reforçada",
                "Investimento em tropas de elite para proteger o palácio.",
                -15, 0, +15, +5
        ));

        // === SOCIAIS ===
        politicasDisponiveis.add(new Politica(
                "Distribuição de Pão",
                "Pão gratuito para os mais necessitados.",
                -10, +15, 0, +5
        ));
        politicasDisponiveis.add(new Politica(
                "Construção de Hospitais",
                "Reduz mortalidade e aumenta a saúde do povo.",
                -20, +10, 0, +8
        ));
        politicasDisponiveis.add(new Politica(
                "Festival Real Anual",
                "Um grande festival em honra ao rei todo ano.",
                -15, +10, 0, +10
        ));

        // === ECONÔMICAS ===
        politicasDisponiveis.add(new Politica(
                "Livre Comércio",
                "Abre as fronteiras ao comércio externo.",
                +20, +5, 0, -5
        ));
        politicasDisponiveis.add(new Politica(
                "Mineração Intensa",
                "Explora as minas ao máximo — mais ouro, mais risco.",
                +15, -5, -5, -5
        ));
        politicasDisponiveis.add(new Politica(
                "Subsídio Agrícola",
                "Apoia os agricultores com sementes e ferramentas.",
                -10, +10, 0, +5
        ));
    }


    public List<Politica> getPoliticasDisponiveis() {
        return new ArrayList<>(politicasDisponiveis);
    }

    public Politica buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {}

        return politicasDisponiveis.stream()
                .filter(p -> p.getTitulo().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public Politica buscarPorIndice(int indice) {

        if (indice < 0 || indice >= politicasDisponiveis.size()){
            throw new IllegalArgumentException("Indice invalido");
        };
        return politicasDisponiveis.get(indice);
    }

}