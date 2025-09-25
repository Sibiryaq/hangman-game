package ru.sibiryaq;


import ru.sibiryaq.exception.DictionaryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
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
            String devPath = Paths.get("src/main/resources" + dictionaryPath).toAbsolutePath().toString();
            throw new DictionaryException(
                    "Dictionary not found: " + dictionaryPath + " (dev: " + devPath + ")"
            );
        }

        try (var reader = new BufferedReader(
                new InputStreamReader(resource.openStream(), StandardCharsets.UTF_8))) {

            List<String> words = reader.lines()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(w -> w.length() >= minWordLength)
                    .collect(Collectors.toList());

            if (words.isEmpty()) {
                String where = "file".equalsIgnoreCase(resource.getProtocol())
                        ? Paths.get(resource.getPath()).toAbsolutePath().toString()
                        : resource.toString();
                throw new DictionaryException("Dictionary is empty: " + where);
            }
            return words;

        } catch (IOException e) {
            String where = "file".equalsIgnoreCase(resource.getProtocol())
                    ? Paths.get(resource.getPath()).toAbsolutePath().toString()
                    : resource.toString();
            throw new DictionaryException("Failed to read dictionary: " + where, e);
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