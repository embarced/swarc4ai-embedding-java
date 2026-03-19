# Anleitung fuer Studierende: Java-Uebung "Embeddings"

## Ziel der Uebung
In dieser Uebung arbeitet ihr euch schrittweise von einfachen, selbstgebauten Vektoren zu echten OpenAI-Embeddings vor:

1. Texte in Tokens zerlegen
2. One-Hot-Encoding und Bag of Words verstehen
3. Cosine Similarity berechnen
4. Grenzen von Bag of Words beobachten
5. dieselben Texte mit OpenAI embeddieren
6. die Similarity-Werte vergleichen

Am Ende steht ein kleines Java-Programm, das denselben Satzpaaren zwei verschiedene Embedding-Strategien gegenueberstellt.

## Voraussetzungen
- Java 21 installiert
- Maven installiert (`mvn`)
- fuer `ex-2` und `main`: OpenAI API Key gesetzt

API-Key setzen:

```bash
export OPENAI_API_KEY="<dein-key>"
```

## Arbeitsweise
Dieses Repository enthaelt:
- die Branch `ex-1` mit der ersten Uebung zu manuellen Vektoren
- die Branch `ex-2` mit der geloesten Uebung 1 und einer neuen Uebung zu OpenAI-Embeddings
- die Branch `main` mit der vollstaendigen Referenzloesung

Wichtig:
- `// TODO`-Texte und `// SO PRUEFST DU ES` sind auf Deutsch
- Klassen- und Variablennamen bleiben Englisch

## Branch-Ablauf
### 1) Uebungs-Branch: `ex-1`

```bash
git checkout ex-1
```

Sucht TODOs nach `UEBUNG 1`.

Inhaltlich:
- Vokabular aus mehreren Texten aufbauen
- einen Token per One-Hot-Encoding darstellen
- einen Text als Bag-of-Words-Vektor abbilden
- Cosine Similarity zwischen zwei Textvektoren berechnen
- beobachten, wo Bag of Words an Grenzen stoesst

Testidee:
- App starten
- pruefen, dass fuer mehrere Satzpaare Bag-of-Words-Scores ausgegeben werden
- beobachten, dass Synonyme schwach erkannt werden
- beobachten, dass Wortreihenfolge bei Bag of Words verloren geht

### 2) Uebungs-Branch: `ex-2`

```bash
git checkout ex-2
```

Sucht TODOs nach `UEBUNG 2`.

Inhaltlich:
- Texte mit dem Spring-AI-`EmbeddingModel` embedden
- Cosine Similarity fuer die Embeddings berechnen
- Bag-of-Words und OpenAI-Embeddings direkt vergleichen

Testidee:
- App starten
- beobachten, dass semantisch aehnliche Satzpaare mit OpenAI oft naeher zusammenliegen als bei Bag of Words

### 3) Referenzloesung: `main`

```bash
git checkout main
```

`main` enthaelt die vollstaendige, lauffaehige Loesung.

## Anwendung starten

```bash
mvn -q -DskipTests compile
mvn spring-boot:run
```

## Nuetzliche Kommandos
TODOs finden:

```bash
rg "UEBUNG" src
```

Status pruefen:

```bash
git status
git diff
```

Branch wechseln:

```bash
git checkout <branch-name>
```

## Wichtige Dateien
- `src/main/java/de/landtag/embedding/EmbeddingApplication.java`
  - Startpunkt und Konsolenausgabe
- `src/main/java/de/landtag/embedding/service/ManualEmbeddingService.java`
  - One-Hot-Encoding, Bag of Words und Cosine Similarity
- `src/main/java/de/landtag/embedding/service/OpenAiEmbeddingService.java`
  - OpenAI-Embeddings fuer dieselben Texte

## Abschluss
Wenn ihr die TODOs in `ex-1` und `ex-2` geloest habt, vergleicht mit:

```bash
git checkout main
```
