# Anleitung zur Java-Übung "Embeddings"

## Ziel der Übung
In dieser Übung arbeitet ihr euch schrittweise von einfachen, selbstgebauten Vektoren zu echten OpenAI-Embeddings vor:

1. Texte in Tokens zerlegen
2. One-Hot-Encoding und Bag of Words verstehen
3. Cosine Similarity berechnen
4. Grenzen von Bag of Words beobachten
5. dieselben Texte mit OpenAI embeddieren
6. die Similarity-Werte vergleichen

Am Ende steht ein kleines Java-Programm, das dieselben Satzpaare mit zwei verschiedenen Embedding-Strategien gegenüberstellt.

## Voraussetzungen
- Java 21 installiert
- Maven installiert (`mvn`)
- für `ex-2` und `main`: OpenAI API Key gesetzt

API-Key setzen:

```bash
export OPENAI_API_KEY="<dein-key>"
```

## Arbeitsweise
Dieses Repository enthält:
- die Branch `ex-1` mit der ersten Übung zu manuellen Vektoren
- die Branch `ex-2` mit der gelösten Übung 1 und einer neuen Übung zu OpenAI-Embeddings
- die Branch `main` mit der vollständigen Referenzlösung

Wichtig:
- `// TODO`-Texte und `// SO PRUEFST DU ES` sind auf Deutsch
- Klassen- und Variablennamen bleiben Englisch

## Branch-Ablauf
### 1) Übungs-Branch: `ex-1`

```bash
git checkout ex-1
```

Sucht TODOs nach `UEBUNG 1`.

Inhaltlich:
- Vokabular aus mehreren Texten aufbauen
- einen Token per One-Hot-Encoding darstellen
- einen Text als Bag-of-Words-Vektor abbilden
- Cosine Similarity zwischen zwei Textvektoren berechnen
- beobachten, wo Bag of Words an Grenzen stößt

Testidee:
- App starten
- prüfen, dass für mehrere Satzpaare Bag-of-Words-Scores ausgegeben werden
- beobachten, dass Synonyme schwach erkannt werden
- beobachten, dass Wortreihenfolge bei Bag of Words verloren geht

### 2) Übungs-Branch: `ex-2`

```bash
git checkout ex-2
```

Sucht TODOs nach `UEBUNG 2`.

Inhaltlich:
- Texte mit dem Spring-AI-`EmbeddingModel` embedden
- Cosine Similarity für die Embeddings berechnen
- Bag-of-Words und OpenAI-Embeddings direkt vergleichen

Testidee:
- App starten
- beobachten, dass semantisch ähnliche Satzpaare mit OpenAI oft näher zusammenliegen als bei Bag of Words

### 3) Referenzlösung: `main`

```bash
git checkout main
```

`main` enthält die vollständige, lauffähige Lösung.

## Anwendung starten

```bash
mvn -q -DskipTests compile
mvn spring-boot:run
```

## Nützliche Kommandos
TODOs finden:

```bash
rg "UEBUNG" src
```

Status prüfen:

```bash
git status
git diff
```

Branch wechseln:

```bash
git checkout <branch-name>
```

## Wichtige Dateien
- `src/main/java/de/embarc/ai/EmbeddingApplication.java`
  - Startpunkt und Konsolenausgabe
- `src/main/java/de/embarc/ai/service/ManualEmbeddingService.java`
  - One-Hot-Encoding, Bag of Words und Cosine Similarity
- `src/main/java/de/embarc/ai/service/OpenAiEmbeddingService.java`
  - OpenAI-Embeddings für dieselben Texte

## Abschluss
Wenn die TODOs in `ex-1` und `ex-2` gelöst sind, kann mit folgendem Stand verglichen werden:

```bash
git checkout main
```
