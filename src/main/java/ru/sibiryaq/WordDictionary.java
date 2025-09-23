package ru.sibiryaq;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordDictionary {
    private final List<String> words;
    private final Random random = new Random();
    private final String dictionaryPath;
    private final int minWordLength;

    public WordDictionary(String dictionaryPath, int minWordLength) {
        this.dictionaryPath = dictionaryPath;
        this.minWordLength = minWordLength;
        this.words = load();
    }

    private List<String> load() {
        URL resource = getClass().getResource(dictionaryPath);

        if (resource == null) {
            System.err.printf(
                    "Ошибка: файл словаря не найден: %s%n(ожидался путь: %s)%nРабота программы завершена.%n",
                    dictionaryPath,
                    Paths.get("src/main/resources" + dictionaryPath).toAbsolutePath()
            );
            System.exit(1);
        }

        try (var reader = new BufferedReader(
                new InputStreamReader(resource.openStream(), StandardCharsets.UTF_8))) {

            return reader.lines()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(word -> word.length() >= minWordLength)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.err.printf("Ошибка чтения файла словаря: %s. Работа программы завершена.%n",
                    Paths.get(resource.getPath()).toAbsolutePath());
            System.exit(1);
            return Collections.emptyList();
        }
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            System.err.printf(
                    "Ошибка: файл словаря пуст: %s%nРабота программы будет завершена.%n",
                    Paths.get("src/main/resources" + dictionaryPath).toAbsolutePath()
            );
            System.exit(1);
        }
        return words.get(random.nextInt(words.size()));
    }
}