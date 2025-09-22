package ru.sibiryaq;

import java.util.*;

public class HangmanGame {
    private final WordDictionary dictionary;
    private String secretWord;
    private Set<Character> guessedLetters;
    private Set<Character> wrongLetters;
    private final int MAX_WRONG_GUESSES;

    public HangmanGame() {
        this.dictionary = new WordDictionary();
        this.MAX_WRONG_GUESSES = HangmanPicture.getMaxPictures() - 1;
    }

    public void startGame() {
        initializeGame();
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            displayState();
            char letter = readLetter(scanner);
            processGuess(letter);
        }

        displayResult();
    }

    private void initializeGame() {
        this.secretWord = dictionary.getRandomWord();
        this.guessedLetters = new HashSet<>();
        this.wrongLetters = new HashSet<>();
    }

    private char readLetter(Scanner scanner) {
        while (true) {
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return input.charAt(0);
            }
            System.out.println("Пожалуйста, введите одну букву.");
        }
    }

    private void processGuess(char letter) {
        if (isAlreadyTried(letter)) {
            System.out.println("Вы уже пробовали эту букву!");
            return;
        }

        if (secretWord.indexOf(letter) >= 0) {
            addCorrectGuess(letter);
        } else {
            addWrongGuess(letter);
        }
    }

    private boolean isAlreadyTried(char letter) {
        return guessedLetters.contains(letter) || wrongLetters.contains(letter);
    }

    private void addCorrectGuess(char letter) {
        guessedLetters.add(letter);
    }

    private void addWrongGuess(char letter) {
        wrongLetters.add(letter);
        System.out.println("Нет такой буквы!");
    }

    private boolean isWin() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) return false;
        }
        return true;
    }

    private boolean isLose() {
        return wrongLetters.size() >= MAX_WRONG_GUESSES;
    }

    private boolean isGameOver() {
        return isWin() || isLose();
    }

    private void displayState() {
        System.out.println("\n" + getMaskedWord());
        System.out.println("Ошибки: " + wrongLetters.size() + "/" + MAX_WRONG_GUESSES);
        HangmanPicture.printPicture(wrongLetters.size());
        System.out.println("Использованные буквы: " + getUsedLetters());
    }

    private String getMaskedWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            sb.append(guessedLetters.contains(c) ? c : "_").append(" ");
        }
        return sb.toString().trim();
    }

    private String getUsedLetters() {
        Set<Character> all = new TreeSet<>(Comparator.naturalOrder());
        all.addAll(guessedLetters);
        all.addAll(wrongLetters);
        return all.toString();
    }

    private void displayResult() {
        System.out.println("\n--- РЕЗУЛЬТАТ ---");
        if (isWin()) {
            System.out.println("ПОБЕДА! Вы отгадали слово: " + secretWord.toUpperCase());
        } else {
            HangmanPicture.printPicture(MAX_WRONG_GUESSES);
            System.out.println("ПОРАЖЕНИЕ! Загаданное слово: " + secretWord.toUpperCase());
        }
    }
}