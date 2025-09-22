package ru.sibiryaq;

public class HangmanDrawer {

    private static final String[] STAGES = {
            "   _______\n" +
                    "  |       |\n" +
                    "  |\n" +
                    "  |\n" +
                    "  |\n" +
                    "  |\n" +
                    "__|__",

            "   _______\n" +
                    "  |       |\n" +
                    "  |       O\n" +
                    "  |\n" +
                    "  |\n" +
                    "  |\n" +
                    "__|__",

            "   _______\n" +
                    "  |       |\n" +
                    "  |       O\n" +
                    "  |       |\n" +
                    "  |\n" +
                    "  |\n" +
                    "__|__",

            "   _______\n" +
                    "  |       |\n" +
                    "  |       O\n" +
                    "  |      /|\n" +
                    "  |\n" +
                    "  |\n" +
                    "__|__",

            "   _______\n" +
                    "  |       |\n" +
                    "  |       O\n" +
                    "  |      /|\\\n" +
                    "  |\n" +
                    "  |\n" +
                    "__|__",

            "   _______\n" +
                    "  |       |\n" +
                    "  |       O\n" +
                    "  |      /|\\\n" +
                    "  |      /\n" +
                    "  |\n" +
                    "__|__",

            "   _______\n" +
                    "  |       |\n" +
                    "  |       O\n" +
                    "  |      /|\\\n" +
                    "  |      / \\\n" +   // Здесь тоже: " \\"
                    "  |\n" +
                    "__|__"
    };

    public void draw(int stage) {
        if (stage < 0 || stage >= STAGES.length) {
            stage = 0;
        }
        System.out.println(STAGES[stage]);
    }
}
