package de.embarc.ai.service;

import de.embarc.ai.model.TextPair;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class OpenAiEmbeddingService {

    private final EmbeddingModel embeddingModel;

    public OpenAiEmbeddingService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public double calculateSimilarity(String leftText, String rightText) {
        // TODO: UEBUNG 2 - Erzeuge fuer den linken Text ein Embedding ueber das Spring-AI-EmbeddingModel.
        // TODO: UEBUNG 2 - Erzeuge fuer den rechten Text ein Embedding ueber das Spring-AI-EmbeddingModel.
        // TODO: UEBUNG 2 - Berechne die Cosine Similarity zwischen beiden Embeddings.
        // TODO: UEBUNG 2 - Gib den berechneten Wert zurueck.
        // SO PRUEFST DU ES: Starte die App mit gesetztem OPENAI_API_KEY. Fuer semantisch aehnliche Satzpaare
        // wie 'Auto' vs. 'Fahrzeug' sollte der Score meist hoeher und sinnvoller sein als bei Bag of Words.
        throw new UnsupportedOperationException("UEBUNG 2: Implementiere die OpenAI-Embeddings und den Similarity-Vergleich.");
    }

    public void printComparison(TextPair pair) {
        double similarity = calculateSimilarity(pair.leftText(), pair.rightText());

        System.out.println("\n[OpenAI Embeddings] " + pair.title());
        System.out.println("A: " + pair.leftText());
        System.out.println("B: " + pair.rightText());
        System.out.printf(Locale.US, "Cosine Similarity: %.4f%n", similarity);
        System.out.println("Beobachtung: " + pair.explanation());
    }
}
