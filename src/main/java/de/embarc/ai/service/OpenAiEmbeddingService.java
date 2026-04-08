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
        float[] leftEmbedding = embeddingModel.embed(leftText);
        float[] rightEmbedding = embeddingModel.embed(rightText);
        return CosineSimilarity.calculate(leftEmbedding, rightEmbedding);
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
