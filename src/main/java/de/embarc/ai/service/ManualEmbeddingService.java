package de.embarc.ai.service;

import de.embarc.ai.model.TextPair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class ManualEmbeddingService {

    public List<String> buildVocabulary(List<String> texts) {
        Set<String> vocabulary = new LinkedHashSet<>();
        for (String text : texts) {
            vocabulary.addAll(tokenize(text));
        }
        return new ArrayList<>(vocabulary);
    }

    public int[] oneHotEncode(String token, List<String> vocabulary) {
        int[] vector = new int[vocabulary.size()];
        int tokenIndex = vocabulary.indexOf(token.toLowerCase(Locale.ROOT));
        if (tokenIndex >= 0) {
            vector[tokenIndex] = 1;
        }
        return vector;
    }

    public double[] toBagOfWordsVector(String text, List<String> vocabulary) {
        double[] vector = new double[vocabulary.size()];
        for (String token : tokenize(text)) {
            int tokenIndex = vocabulary.indexOf(token);
            if (tokenIndex >= 0) {
                vector[tokenIndex] += 1.0;
            }
        }
        return vector;
    }

    public double calculateSimilarity(String leftText, String rightText, List<String> vocabulary) {
        double[] leftVector = toBagOfWordsVector(leftText, vocabulary);
        double[] rightVector = toBagOfWordsVector(rightText, vocabulary);
        return CosineSimilarity.calculate(leftVector, rightVector);
    }

    public List<String> tokenize(String text) {
        return Arrays.stream(text.toLowerCase(Locale.ROOT).split("[^\\p{L}\\p{Nd}]+"))
                .filter(token -> !token.isBlank())
                .toList();
    }

    public void printOneHotExample(List<String> vocabulary, String token) {
        int[] vector = oneHotEncode(token, vocabulary);
        System.out.println("One-Hot-Beispiel für Token '" + token + "': " + Arrays.toString(vector));
    }

    public void printComparison(TextPair pair, List<String> vocabulary) {
        double similarity = calculateSimilarity(pair.leftText(), pair.rightText(), vocabulary);

        System.out.println("\n[Bag of Words] " + pair.title());
        System.out.println("A: " + pair.leftText());
        System.out.println("B: " + pair.rightText());
        System.out.printf(Locale.US, "Cosine Similarity: %.4f%n", similarity);
        System.out.println("Beobachtung: " + pair.explanation());
    }
}
