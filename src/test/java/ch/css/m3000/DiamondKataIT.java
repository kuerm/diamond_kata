package ch.css.m3000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiamondKataIT {
    @Test
    void diamondWhenWrongInputSeedThenReturnInvalidInputAsString() {
        DiamondKata diamondKata = new DiamondKata();

        String actual = diamondKata.diamond('0');

        assertThat("Invalid Input").isEqualTo(actual);
    }

    @Test
    void diamondWhenInputSeedIsAThenReturnA() {
        DiamondKata diamondKata = new DiamondKata();

        String actual = diamondKata.diamond('A');

        assertThat("A").isEqualTo(actual);
    }

    @Test
    void diamondWhenInputSeedIsLowerCaseBThenReturnABBA() {
        DiamondKata diamondKata = new DiamondKata();

        String actual = diamondKata.diamond('b');

        assertThat("""
                 A
                B B
                 A""")
                .isEqualTo(actual);
    }

    @Test
    void diamondWhenInputSeedIsDThenReturnDiamond() {
        DiamondKata diamondKata = new DiamondKata();

        String actual = diamondKata.diamond('D');

        assertThat("""
                   A
                  B B
                 C   C
                D     D
                 C   C
                  B B
                   A""")
                .isEqualTo(actual);
    }

    private char[] detectCharacters(char seed) {
        char[] characters = new char[seed - 'A' + 1];
        for (int i = 0; i < characters.length; i++) {
            characters[i] = (char) ('A' + i);
        }
        return characters;
    }

    private char validate(char seed) {
        return Character.toUpperCase(seed);
    }

    private String getString(IndentationChar[] indentationChars) {
        String returnvalue = "";
        for (int i = 0; i < indentationChars.length; i++) {
            returnvalue += indentationChars[i].character;
        }
        return returnvalue;
    }

    private IndentationChar[] getIndentation(char[] characters) {
        int totalCharacters = characters.length;
        IndentationChar[] indentationChars = new IndentationChar[totalCharacters];

        int j = 0;
        for (int i = totalCharacters - 1; i >= 0; i--) {
            indentationChars[j++] = new IndentationChar(characters[i], i);
        }
        return indentationChars;
    }

    private record IndentationChar(char character,
                                   int indentation) {

    }

    class DiamondKata {
        public String diamond(char seed) {
            char validatedSeed = validate(seed);
            char[] characters = detectCharacters(validatedSeed);
            // detect on how many characters has to be displayed how much indentation every one has
            IndentationChar[] indentationChars = getIndentation(characters);
            // display
            String diamond = getString(indentationChars);
            return diamond;
        }
    }
}
