package ru.sibiryaq;

import java.util.Scanner;

public class Main {
    private final static String START = "1";
    private final static String QUIT = "2";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HangmanGame game = new HangmanGame();


        while (true) {
            System.out.println("===Виселица===");
            System.out.println(START + ".Начать новую игру");
            System.out.println(QUIT + ".Выйти");
            System.out.println("Выберите действие: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case START:
                    game.startGame();
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
