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
        // TODO: ÜBUNG 1 - Erzeuge aus allen Texten ein gemeinsames Vokabular.
        // TODO: ÜBUNG 1 - Zerlege dafür jeden Text in Tokens und sammle jedes Wort genau einmal.
        // TODO: ÜBUNG 1 - Gib das Vokabular als List<String> zurück.
        throw new UnsupportedOperationException("ÜBUNG 1: Implementiere den Aufbau des Vokabulars.");
    }

    public int[] oneHotEncode(String token, List<String> vocabulary) {
        // TODO: ÜBUNG 1 - Erzeuge einen Vektor mit der Länge des Vokabulars.
        // TODO: ÜBUNG 1 - Setze genau die Position des übergebenen Tokens auf 1.
        // TODO: ÜBUNG 1 - Alle anderen Positionen bleiben 0.
        throw new UnsupportedOperationException("ÜBUNG 1: Implementiere One-Hot-Encoding.");
    }

    public double[] toBagOfWordsVector(String text, List<String> vocabulary) {
        // TODO: ÜBUNG 1 - Erzeuge für den gesamten Text einen Bag-of-Words-Vektor.
        // TODO: ÜBUNG 1 - Erhöhe für jedes vorkommende Token den passenden Zähler im Vektor.
        throw new UnsupportedOperationException("ÜBUNG 1: Implementiere den Bag-of-Words-Vektor.");
    }

    public double calculateSimilarity(String leftText, String rightText, List<String> vocabulary) {
        // TODO: ÜBUNG 1 - Erzeuge für beide Texte jeweils einen Bag-of-Words-Vektor.
        // TODO: ÜBUNG 1 - Berechne anschließend die Cosine Similarity mit der Hilfsklasse CosineSimilarity.
        // TODO: ÜBUNG 1 - Gib den berechneten Wert zurück.
        // SO PRÜFST DU ES: Starte die App. Es sollten für mehrere Satzpaare Bag-of-Words-Scores ausgegeben werden.
        // Achtet darauf, dass 'Auto' vs. 'Fahrzeug' nur begrenzt erkannt wird und dass bei
        // 'Hund beißt Mann' vs. 'Mann beißt Hund' die Wortreihenfolge verloren geht.
        throw new UnsupportedOperationException("ÜBUNG 1: Implementiere die Bag-of-Words-Similarity.");
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
