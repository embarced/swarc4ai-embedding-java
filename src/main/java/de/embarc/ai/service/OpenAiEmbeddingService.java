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
        // TODO: ÜBUNG 2 - Erzeuge für den linken Text ein Embedding über das Spring-AI-EmbeddingModel.
        // TODO: ÜBUNG 2 - Erzeuge für den rechten Text ein Embedding über das Spring-AI-EmbeddingModel.
        // TODO: ÜBUNG 2 - Berechne die Cosine Similarity zwischen beiden Embeddings.
        // TODO: ÜBUNG 2 - Gib den berechneten Wert zurück.
        // SO PRÜFST DU ES: Starte die App mit gesetztem OPENAI_API_KEY. Für semantisch ähnliche Satzpaare
        // wie 'Auto' vs. 'Fahrzeug' sollte der Score meist höher und sinnvoller sein als bei Bag of Words.
        throw new UnsupportedOperationException("ÜBUNG 2: Implementiere die OpenAI-Embeddings und den Similarity-Vergleich.");
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
