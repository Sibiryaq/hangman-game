package ru.sibiryaq;

import ru.sibiryaq.exception.DictionaryException;

import java.util.Scanner;

public class Main {
    private static final String START = "1";
    private static final String QUIT  = "2";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            HangmanGame game;
            try {
                game = new HangmanGame();
            } catch (DictionaryException e) {
                System.err.println("Не удалось загрузить словарь: " + e.getMessage());
                System.err.println("Программа будет завершена.");
                return;
            }

            while (true) {
                System.out.println("=== Виселица ===");
                System.out.println(START + ". Начать новую игру");
                System.out.println(QUIT  + ". Выйти");
                System.out.print("Выберите действие: ");

                String input = scanner.nextLine().trim();

                switch (input) {
                    case START:
                        try {
                            game.startGame();
                        } catch (DictionaryException e) {
                            System.err.println("Ошибка словаря при запуске игры: " + e.getMessage());
                            System.err.println("Программа будет завершена.");
                            return;
                        } catch (Exception e) {
                            System.err.println("Неожиданная ошибка: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                            return;
                        }
                        break;

                    case QUIT:
                        System.out.println("Выходим..");
                        return;

                    default:
                        System.out.println("Неверный ввод. Попробуй снова.");
                }
            }
        }
    }
}