package ru.sibiryaq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class WordDictionary {

    private final List<String> words;
    private final Random random = new Random();

    public WordDictionary() {
        this.words = loadWords();
    }

    private List<String> loadWords() {
        try (var reader = new BufferedReader( new InputStreamReader(
                        Objects.requireNonNull(getClass().getResourceAsStream("/words.txt")),
                        StandardCharsets.UTF_8))) {

            return reader.lines()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(word -> word.length() >= 4)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить словарь", e);
        }
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException("Словарь пуст");
        }
        return words.get(random.nextInt(words.size()));
    }
}