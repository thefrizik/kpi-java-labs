import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Лабораторна робота №4 (Композиція класів)
 * Варіант 17
 */

// Базовий інтерфейс для елементів речення
interface SentenceElement {
    String getAsString();
}

// Клас Літера
class Letter {
    private char character;

    public Letter(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}

// Клас Слово (масив літер)
class Word implements SentenceElement {
    private Letter[] letters;

    public Word(String wordString) {
        letters = new Letter[wordString.length()];
        for (int i = 0; i < wordString.length(); i++) {
            letters[i] = new Letter(wordString.charAt(i));
        }
    }

    @Override
    public String getAsString() {
        StringBuilder sb = new StringBuilder();
        for (Letter l : letters) {
            sb.append(l.getCharacter());
        }
        return sb.toString();
    }
    
    // Для порівняння без урахування регістру
    public String getLowerCaseString() {
        return getAsString().toLowerCase();
    }
}

// Клас Розділовий знак
class Punctuation implements SentenceElement {
    private char symbol;

    public Punctuation(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getAsString() {
        return String.valueOf(symbol);
    }
}

// Клас Речення (масив слів та розділових знаків)
class Sentence {
    private SentenceElement[] elements;

    public Sentence(String sentenceString) {
        List<SentenceElement> elemList = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < sentenceString.length(); i++) {
            char c = sentenceString.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                currentWord.append(c);
            } else {
                if (currentWord.length() > 0) {
                    elemList.add(new Word(currentWord.toString()));
                    currentWord.setLength(0);
                }
                if (c != ' ') { // Пробіли опускаємо, вони роздільники
                    elemList.add(new Punctuation(c));
                }
            }
        }
        if (currentWord.length() > 0) {
            elemList.add(new Word(currentWord.toString()));
        }

        elements = elemList.toArray(new SentenceElement[0]);
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        for (SentenceElement el : elements) {
            if (el instanceof Word) {
                words.add((Word) el);
            }
        }
        return words;
    }

    public String getOriginalSentence() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] instanceof Word && i > 0 && elements[i-1] instanceof Word) {
                sb.append(" ");
            }
            sb.append(elements[i].getAsString());
        }
        return sb.toString();
    }
}

// Клас Текст (масив речень)
class Text {
    private Sentence[] sentences;

    public Text(String rawText) {
        // Заміна послідовності пробілів і табуляцій одним пробілом
        String cleanedText = rawText.replaceAll("[\\t ]+", " ").trim();

        // Розбиття на речення за знаками кінця (., !, ?)
        String[] sentenceStrings = cleanedText.split("(?<=[.!?])\\s*");
        sentences = new Sentence[sentenceStrings.length];
        
        for (int i = 0; i < sentenceStrings.length; i++) {
            sentences[i] = new Sentence(sentenceStrings[i]);
        }
    }

    public Sentence[] getSentences() {
        return sentences;
    }
}

// Виконавчий клас
public class Lab4 {
    public static void main(String[] args) {
        // Початковий текст з табуляціями та пробілами
        String rawInputText = "Java   is a  programming\tlanguage. Java is object-oriented! " +
                              "Many developers    love Java. \t Python is also a programming language, but Java is unique.";
                              
        System.out.println("Оригінальний текст:\n" + rawInputText + "\n");
        
        Text text = new Text(rawInputText);
        
        // Знайти найбільшу кількість речень заданого тексту, в яких є однакові слова.
        Map<String, Integer> wordSentenceCount = new HashMap<>();
        
        for (Sentence sentence : text.getSentences()) {
            Set<String> uniqueWordsInSentence = new HashSet<>();
            for (Word word : sentence.getWords()) {
                uniqueWordsInSentence.add(word.getLowerCaseString());
            }
            
            for (String w : uniqueWordsInSentence) {
                wordSentenceCount.put(w, wordSentenceCount.getOrDefault(w, 0) + 1);
            }
        }
        
        int maxSentences = 0;
        String mostFrequentWord = "";
        
        for (Map.Entry<String, Integer> entry : wordSentenceCount.entrySet()) {
            if (entry.getValue() > maxSentences) {
                maxSentences = entry.getValue();
                mostFrequentWord = entry.getKey();
            }
        }
        
        System.out.println("--- Результат виконання дії ---");
        if (maxSentences > 0) {
            System.out.println("Найбільша кількість речень, в яких зустрічається однакове слово: " + maxSentences);
            System.out.println("Це слово: \"" + mostFrequentWord + "\"");
        } else {
            System.out.println("Слів не знайдено.");
        }
    }
}
