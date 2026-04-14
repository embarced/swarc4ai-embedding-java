package de.embarc.ai;

import de.embarc.ai.model.TextPair;
import de.embarc.ai.service.ManualEmbeddingService;
import de.embarc.ai.service.OpenAiEmbeddingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EmbeddingApplication implements CommandLineRunner {

    private final ManualEmbeddingService manualEmbeddingService;
    private final OpenAiEmbeddingService openAiEmbeddingService;

    public EmbeddingApplication(ManualEmbeddingService manualEmbeddingService, OpenAiEmbeddingService openAiEmbeddingService) {
        this.manualEmbeddingService = manualEmbeddingService;
        this.openAiEmbeddingService = openAiEmbeddingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmbeddingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<TextPair> textPairs = createExamplePairs();
        List<String> allTexts = textPairs.stream()
                .flatMap(pair -> java.util.stream.Stream.of(pair.leftText(), pair.rightText()))
                .toList();

        List<String> vocabulary = manualEmbeddingService.buildVocabulary(allTexts);

        System.out.println("=== Embedding Übung ===");
        System.out.println("Vokabular: " + vocabulary);
        manualEmbeddingService.printOneHotExample(vocabulary, "auto");

        System.out.println("\n=== Teil 1: Bag of Words ===");
        for (TextPair pair : textPairs) {
            manualEmbeddingService.printComparison(pair, vocabulary);
        }

        System.out.println("\n=== Teil 2: OpenAI Embeddings ===");
        for (TextPair pair : textPairs) {
            openAiEmbeddingService.printComparison(pair);
        }
    }

    private List<TextPair> createExamplePairs() {
        return List.of(
                new TextPair(
                        "Synonyme",
                        "Das Auto ist schnell",
                        "Das Fahrzeug ist schnell",
                        "Bag of Words sieht nur die Wortüberlappung. OpenAI erkennt meist besser, dass Auto und Fahrzeug ähnlich sind."
                ),
                new TextPair(
                        "Wortreihenfolge",
                        "Hund beißt Mann",
                        "Mann beißt Hund",
                        "Bag of Words ignoriert die Reihenfolge der Wörter und kann deshalb Bedeutungsunterschiede übersehen."
                ),
                new TextPair(
                        "Klar unähnlich",
                        "Ich trinke morgens Kaffee",
                        "Der Server startet neu",
                        "Beide Verfahren sollten hier eine niedrige Ähnlichkeit liefern."
                ),
                new TextPair(
                        "Support-Sprache",
                        "Passwort vergessen",
                        "Ich kann mich nicht einloggen",
                        "Die Wortwahl ist verschieden, die Bedeutung aber verwandt. Das ist für OpenAI-Embeddings meist leichter."
                )
        );
    }
}
