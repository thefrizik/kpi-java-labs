import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Лабораторна робота №2
 * Варіант: C3=2 (StringBuffer), C17=0 (Знайти найбільшу кількість речень, в яких є однакові слова)
 */
public class Lab2 {
    public static void main(String[] args) {
        // Початковий текст згідно з умовою введення-виведення
        String inputText = "Java is a programming language. Java is object-oriented! " +
                           "Many developers love Java. Python is also a programming language, but Java is unique.";
        
        System.out.println("Текст для аналізу:\n" + inputText + "\n");
        
        StringBuffer buffer = new StringBuffer(inputText);
        
        // Розбиття тексту на речення (враховуємо крапку, знак оклику та питання)
        // Для спрощення роботи з алгоритмом переводимо буфер у String тільки для спліту
        String[] sentences = buffer.toString().split("[.!?]");
        
        // Карта для збереження кількості речень, у яких зустрічається кожне слово
        Map<String, Integer> wordSentenceCount = new HashMap<>();
        
        for (String sentenceStr : sentences) {
            StringBuffer sentenceBuf = new StringBuffer(sentenceStr.trim().toLowerCase());
            if (sentenceBuf.length() == 0) continue;
            
            // Заміна знаків пунктуації на пробіли всередині речення
            for (int i = 0; i < sentenceBuf.length(); i++) {
                char c = sentenceBuf.charAt(i);
                if (!Character.isLetterOrDigit(c) && c != ' ') {
                    sentenceBuf.setCharAt(i, ' ');
                }
            }
            
            String[] words = sentenceBuf.toString().split("\\s+");
            Set<String> uniqueWordsInSentence = new HashSet<>();
            
            for (String word : words) {
                if (!word.isEmpty()) {
                    uniqueWordsInSentence.add(word);
                }
            }
            
            // Додаємо слова до загального лічильника
            for (String word : uniqueWordsInSentence) {
                wordSentenceCount.put(word, wordSentenceCount.getOrDefault(word, 0) + 1);
            }
        }
        
        // Знаходження найбільшої кількості речень для одного слова
        int maxSentences = 0;
        String mostFrequentWord = "";
        
        for (Map.Entry<String, Integer> entry : wordSentenceCount.entrySet()) {
            if (entry.getValue() > maxSentences) {
                maxSentences = entry.getValue();
                mostFrequentWord = entry.getKey();
            }
        }
        
        System.out.println("Результат аналізу:");
        if (maxSentences > 0) {
            System.out.println("Найбільша кількість речень, в яких є однакове слово: " + maxSentences);
            System.out.println("Це слово: \"" + mostFrequentWord + "\"");
        } else {
            System.out.println("Слів не знайдено.");
        }
    }
}
