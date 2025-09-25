package ru.sibiryaq;

public class HangmanRenderer {

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

    public static void renderPicture(int numPicture) {
        if (numPicture < 0 || numPicture >= PICTURES.length) {
            throw new IllegalArgumentException("Картинка с номером " + numPicture + " не существует");
        }
        System.out.println(PICTURES[numPicture]);
    }

    public static int getMaxPictures() {
        return PICTURES.length;
    }
}