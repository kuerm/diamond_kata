package ch.css.m3000;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class DiamondKataIT {
    DiamondKata sut = new DiamondKata();

    @Test
    void diamondWhenWrongInputSeedThenReturnInvalidInputAsString() {
        String actual = sut.diamond('0');

        assertThat("Invalid Input").isEqualTo(actual);
    }

    @Test
    void diamondWhenInputSeedIsAThenReturnA() {
        String actual = sut.diamond('A');

        assertThat("A").isEqualTo(actual);
    }

    @Test
    void diamondWhenInputSeedIsLowerCaseBThenReturnABBA() {

        String actual = sut.diamond('b');

        assertThat("""
                 A
                B B
                 A""")
                .isEqualTo(actual);
    }

    @Test
    void diamondWhenInputSeedIsDThenReturnDiamond() {


        String actual = sut.diamond('D');

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

    @Test
    void getIndentationWhenTwoCharactersThenReturnFirstCharacterWith0AndSecondWith1() {
        IndentationChar[] actual = sut.getIndentation(new char[]{'*', 's'});

        assertThat(actual)
                .containsExactly(new IndentationChar('*', 1), new IndentationChar('s', 0));
    }

    record IndentationChar(char character,
                           int indentation) {
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            IndentationChar that = (IndentationChar) object;
            return character == that.character && indentation == that.indentation;
        }

        @Override
        public int hashCode() {
            return Objects.hash(character, indentation);
        }
    }

    class DiamondKata {

        private char validate(char seed) {
            return Character.toUpperCase(seed);
        }

        private char[] detectCharacters(char seed) {
            char[] characters = new char[seed - 'A' + 1];
            for (int i = 0; i < characters.length; i++) {
                characters[i] = (char) ('A' + i);
            }
            return characters;
        }

        private String getString(IndentationChar[] indentationChars) {
            StringBuilder returnvalue = new StringBuilder();
            for (IndentationChar indentationChar : indentationChars) {
                returnvalue.append(indentationChar.character);
            }
            return returnvalue.toString();
        }

        IndentationChar[] getIndentation(char[] characters) {
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
            // detect on how many characters has to be displayed how much indentation every one has
            IndentationChar[] indentationChars = getIndentation(characters);
            // display
            String diamond = getString(indentationChars);
            return diamond;
        }

    }
}
