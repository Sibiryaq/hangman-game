package ru.sibiryaq;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class WordDictionary {
    private final List<String> words;
    private final Random random = new Random();

    public WordDictionary() {
        this.words = load();
    }

    private List<String> load() {
        try (var reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getResourceAsStream("/words.txt")),
                        StandardCharsets.UTF_8))) {

            return reader.lines()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(word -> word.length() >= 4)
                    .collect(Collectors.toList());

        } catch (IOException | NullPointerException e) {
            System.out.println("Ошибка: файл словаря не найден в resources/words.txt. Работа программы завершена.");
            System.exit(1);
            return Collections.emptyList(); // чтобы компилятор не ругался
        }
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            System.out.println("Словарь пуст. Работа программы будет завершена.");
            System.exit(1);
        }
        return words.get(random.nextInt(words.size()));
    }
}