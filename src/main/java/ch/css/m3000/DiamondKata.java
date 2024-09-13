package ch.css.m3000;

public class DiamondKata {

    private static void appendSecondPart(int maxIndentation, StringBuilder result, IndentationChar indentationChar) {
        result.append(" ".repeat(2 * maxIndentation - 2 * indentationChar.indentation() - 1))
                .append(indentationChar.character());
    }

    private static void appendFirstPart(StringBuilder result, IndentationChar indentationChar) {
        result.append(" ".repeat(indentationChar.indentation()))
                .append(indentationChar.character());
    }

    private static void appendNewLine(StringBuilder result) {
        result.append("\n");
    }

    private static boolean isNotFirstNorLastLine(int maxIndentation, IndentationChar indentationChar) {
        return indentationChar.indentation() != maxIndentation;
    }

    private static void concatLine(int maxIndentation, StringBuilder result, IndentationChar indentationChar) {
        appendFirstPart(result, indentationChar);
        if (isNotFirstNorLastLine(maxIndentation, indentationChar)) {
            appendSecondPart(maxIndentation, result, indentationChar);
        }
        appendNewLine(result);
    }

    private static String getLowerDiamondPart(IndentationChar[] indentationChars, int maxIndentation) {
        StringBuilder result = new StringBuilder();
        for (int i = indentationChars.length - 2; i >= 0; i--) {
            IndentationChar indentationChar = indentationChars[i];
            concatLine(maxIndentation, result, indentationChar);
        }
        return result.toString();
    }

    private static String getUpperDiamondPart(IndentationChar[] indentationChars, int maxIndentation) {
        StringBuilder result = new StringBuilder();
        for (IndentationChar indentationChar : indentationChars) {
            concatLine(maxIndentation, result, indentationChar);
        }
        return result.toString();
    }

    private static int calculateMaxIndentation(IndentationChar[] indentationChars) {
        return indentationChars.length - 1;
    }

    private static String concat(String firstPart, String secondPart) {
        return firstPart + secondPart;
    }

    private static String removeLastNewLine(String fullDiamond) {
        return fullDiamond.substring(0, fullDiamond.length() - 1);
    }

    private static boolean isNotALetter(char seed) {
        return !Character.isLetter(seed);
    }

    private char validate(char seed) {
        if (isNotALetter(seed)) {
            throw new IllegalArgumentException("Invalid Input");
        }
        return Character.toUpperCase(seed);
    }

    private char[] detectCharacters(char seed) {
        char[] characters = new char[seed - 'A' + 1];
        for (int i = 0; i < characters.length; i++) {
            characters[i] = (char) ('A' + i);
        }
        return characters;
    }

    private String getString(IndentationChar[] indentationChars, int maxIndentation) {
        String firstPart = getUpperDiamondPart(indentationChars, maxIndentation);
        String secondPart = getLowerDiamondPart(indentationChars, maxIndentation);
        String fullDiamond = concat(firstPart, secondPart);
        return removeLastNewLine(fullDiamond);
    }

    private IndentationChar[] calculateIndentation(char[] characters) {
        int totalCharacters = characters.length;
        IndentationChar[] indentationChars = new IndentationChar[totalCharacters];

        int index = 0;
        for (int i = 0; i < totalCharacters; i++) {
            indentationChars[index++] = new IndentationChar(characters[i], totalCharacters - i - 1);
        }
        return indentationChars;
    }

    public String diamond(char seed) {
        char validatedSeed = validate(seed);
        char[] characters = detectCharacters(validatedSeed);
        IndentationChar[] indentationChars = calculateIndentation(characters);
        int maxIndentation = calculateMaxIndentation(indentationChars);
        return getString(indentationChars, maxIndentation);
    }

}
