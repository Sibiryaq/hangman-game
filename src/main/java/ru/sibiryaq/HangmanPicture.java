package ru.sibiryaq;

public class HangmanPicture {

    private static final String[] PICTURES = {
            """
        _______
        |
        |
        |
        |
        |
        __|__
        """,
            """
        _______
        |       |
        |       O
        |
        |
        |
        __|__
        """,
            """
        _______
        |       |
        |       O
        |       |
        |
        |
        __|__
        """,
            """
        _______
        |       |
        |       O
        |      /|
        |
        |
        __|__
        """,
            """
        _______
        |       |
        |       O
        |      /|\\
        |
        |
        __|__
        """,
            """
        _______
        |       |
        |       O
        |      /|\\
        |      /
        |
        __|__
        """,
            """
        _______
        |       |
        |       O
        |      /|\\
        |      / \\
        |
        __|__
        """
    };

    public static void printPicture(int numPicture) {
        if (numPicture < 0 || numPicture >= PICTURES.length) {
            throw new IllegalArgumentException("Картинка с номером " + numPicture + " не существует");
        }
        System.out.println(PICTURES[numPicture]);
    }

    public static int getMaxPictures() {
        return PICTURES.length;
    }
}