package ru.sibiryaq;

import java.util.*;

public class HangmanGame {
    private final WordDictionary dictionary;
    private final HangmanDrawer drawer;
    private String secretWord;
    private Set<Character> guessedLetters;
    private Set<Character> wrongLetters;
    private static final int MAX_WRONG_GUESSES = 6;

    public HangmanGame() {
        this.dictionary = new WordDictionary();
        this.drawer = new HangmanDrawer();
    }

    public void startNewGame() {
        initializeGame();
        Scanner scanner = new Scanner(System.in);

        while (wrongLetters.size() < MAX_WRONG_GUESSES && !isWordGuessed()) {
            displayState();
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Пожалуйста, введите одну букву.");
                continue;
            }

            char letter = input.charAt(0);
            processGuess(letter);
        }

        displayFinalResult();
    }

    private void initializeGame() {
        this.secretWord = dictionary.getRandomWord();
        this.guessedLetters = new HashSet<>();
        this.wrongLetters = new HashSet<>();
    }

    private void processGuess(char letter) {
        if (guessedLetters.contains(letter) || wrongLetters.contains(letter)) {
            System.out.println("Вы уже пробовали эту букву!");
            return;
        }

        if (secretWord.indexOf(letter) >= 0) {
            guessedLetters.add(letter);
        } else {
            wrongLetters.add(letter);
            System.out.println("Нет такой буквы!");
        }
    }

    private boolean isWordGuessed() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private void displayState() {
        System.out.println("\n" + getMaskedWord());
        System.out.println("Ошибки: " + wrongLetters.size() + "/" + MAX_WRONG_GUESSES);
        drawer.draw(wrongLetters.size());
        System.out.println("Использованные буквы: " + getUsedLetters());
    }

    private String getMaskedWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                sb.append(c).append(" ");
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString().trim();
    }

    private String getUsedLetters() {
        Set<Character> all = new TreeSet<>(Comparator.naturalOrder());
        all.addAll(guessedLetters);
        all.addAll(wrongLetters);
        return all.toString();
    }

    private void displayFinalResult() {
        System.out.println("\n--- РЕЗУЛЬТАТ ---");
        if (isWordGuessed()) {
            System.out.println("ПОБЕДА! Вы отгадали слово: " + secretWord.toUpperCase());
        } else {
            drawer.draw(MAX_WRONG_GUESSES);
            System.out.println("ПОРАЖЕНИЕ! Загаданное слово: " + secretWord.toUpperCase());
        }
    }
}
