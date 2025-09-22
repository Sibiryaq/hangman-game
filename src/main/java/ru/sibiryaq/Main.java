package ru.sibiryaq;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HangmanGame game = new HangmanGame();

        while (true) {
            System.out.println("===Виселица");
            System.out.println("1.Начать новую игру");
            System.out.println("2.Выйти");
            System.out.println("Выберите действие: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    game.startNewGame();
                    break;
                case "2":
                    System.out.println("Выходим..");
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуй снова.");
            }
        }
    }
}
