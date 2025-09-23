package ru.sibiryaq;

import java.util.*;

public class HangmanGame {
    private static final String RUSSIAN_LETTER_REGEX = "[а-яёА-ЯЁ]";
    private static final String DEFAULT_DICTIONARY_PATH = "/words.txt";

    private final WordDictionary dictionary;
    private String secretWord;
    private Set<Character> guessedLetters;
    private Set<Character> wrongLetters;
    private final int maxWrongGuesses;

    public HangmanGame() {
        this.dictionary = new WordDictionary(DEFAULT_DICTIONARY_PATH);
        this.maxWrongGuesses = HangmanRenderer.getMaxPictures() - 1;
    }

    public void startGame() {
        initializeGame();
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            displayState();
            char letter = readPlayerLetter(scanner);
            processGuess(letter);
        }

        displayResult();
    }

    private void initializeGame() {
        this.secretWord = dictionary.getRandomWord();
        this.guessedLetters = new HashSet<>();
        this.wrongLetters = new HashSet<>();
    }

    private char readPlayerLetter(Scanner scanner) {
        while (true) {
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().trim();

            if (input.length() == 1 && isValidRussianLetter(input)) {
                return Character.toLowerCase(input.charAt(0));
            }

            System.out.println("Ошибка ввода! Введите одну русскую букву (А–Я).");
        }
    }

    private boolean isValidRussianLetter(String input) {
        return input != null && input.matches(RUSSIAN_LETTER_REGEX);
    }

    private void processGuess(char letter) {
        if (isUsedLetter(letter)) {
            System.out.println("Вы уже пробовали эту букву!");
            return;
        }

        if (secretWord.indexOf(letter) >= 0) {
            addCorrectGuess(letter);
        } else {
            addWrongLetter(letter);
        }
    }

    private boolean isUsedLetter(char letter) {
        return guessedLetters.contains(letter) || wrongLetters.contains(letter);
    }

    private void addCorrectGuess(char letter) {
        guessedLetters.add(letter);
    }

    private void addWrongLetter(char letter) {
        wrongLetters.add(letter);
        System.out.println("Нет такой буквы!");
    }

    private boolean isWin() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isLose() {
        return wrongLetters.size() >= maxWrongGuesses;
    }

    private boolean isGameOver() {
        return isWin() || isLose();
    }

    private void displayState() {
        System.out.println("\n" + getMaskedWord());
        System.out.printf("Ошибки: %d/%d%n", wrongLetters.size(), maxWrongGuesses);
        HangmanRenderer.renderPicture(wrongLetters.size());
        System.out.println("Использованные буквы: " + getUsedLetters());
    }

    private String getMaskedWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            char symbol = guessedLetters.contains(c) ? c : '_';
            sb.append(symbol).append(" ");
        }
        return sb.toString().trim();
    }

    private String getUsedLetters() {
        Set<Character> usedLetters = new TreeSet<>(Comparator.naturalOrder());
        usedLetters.addAll(guessedLetters);
        usedLetters.addAll(wrongLetters);
        return usedLetters.toString();
    }

    private void displayResult() {
        System.out.println("\n--- РЕЗУЛЬТАТ ---");
        if (isWin()) {
            System.out.println("ПОБЕДА! Вы отгадали слово: " + secretWord.toUpperCase());
        } else {
            HangmanRenderer.renderPicture(maxWrongGuesses);
            System.out.println("ПОРАЖЕНИЕ! Загаданное слово: " + secretWord.toUpperCase());
        }
    }
}