package de.landtag.embedding;

import de.landtag.embedding.model.TextPair;
import de.landtag.embedding.service.ManualEmbeddingService;
import de.landtag.embedding.service.OpenAiEmbeddingService;
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

        System.out.println("=== Embedding Uebung ===");
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
                        "Bag of Words sieht nur die Wortueberlappung. OpenAI erkennt meist besser, dass Auto und Fahrzeug aehnlich sind."
                ),
                new TextPair(
                        "Wortreihenfolge",
                        "Hund beisst Mann",
                        "Mann beisst Hund",
                        "Bag of Words ignoriert die Reihenfolge der Woerter und kann deshalb Bedeutungsunterschiede uebersehen."
                ),
                new TextPair(
                        "Klar unaehnlich",
                        "Ich trinke morgens Kaffee",
                        "Der Server startet neu",
                        "Beide Verfahren sollten hier eine niedrige Aehnlichkeit liefern."
                ),
                new TextPair(
                        "Support-Sprache",
                        "Passwort vergessen",
                        "Ich kann mich nicht einloggen",
                        "Die Wortwahl ist verschieden, die Bedeutung aber verwandt. Das ist fuer OpenAI-Embeddings meist leichter."
                )
        );
    }
}
