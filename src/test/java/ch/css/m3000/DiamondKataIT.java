package ch.css.m3000;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DiamondKataIT {
    DiamondKata sut = new DiamondKata();

    @Test
    void diamondWhenWrongInputSeedThenReturnInvalidInputAsString() {
        char invalidInput = '0';

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> sut.diamond(invalidInput));
    }

    @Test
    void diamondWhenInputSeedIsAThenReturnA() {
        String actual = sut.diamond('A');

        assertThat("A").isEqualTo(actual);
    }

    @Test
    void diamondWhenInputSeedIsLowerCaseBThenReturnABBA() {
        String actual = sut.diamond('b');

        assertThat(actual).isEqualTo("""
                 A
                B B
                 A""");
    }

    @Test
    void diamondWhenInputSeedIsCThenReturnDiamond() {
        String actual = sut.diamond('C');

        assertThat(actual).isEqualTo("""
                  A
                 B B
                C   C
                 B B
                  A""");
    }


    @Test
    void diamondWhenInputSeedIsDThenReturnDiamond() {
        String actual = sut.diamond('D');

        assertThat(actual).isEqualTo("""
                   A
                  B B
                 C   C
                D     D
                 C   C
                  B B
                   A""");
    }

    @Test
    void getIndentationWhenTwoCharactersThenReturnFirstCharacterWith0AndSecondWith1() {
        IndentationChar[] actual = sut.calculateIndentation(new char[]{'*', 's'});

        assertThat(actual)
                .containsExactly(new IndentationChar('*', 1), new IndentationChar('s', 0));
    }

    @Test
    void getIndentationWhenOnlyOneCharacter() {
        IndentationChar[] actual = sut.calculateIndentation(new char[]{'*'});

        assertThat(actual)
                .containsExactly(new IndentationChar('*', 0));
    }

    @Test
    void getStringWhenOnlyOneCharacter() {
        String actual = sut.getString(new IndentationChar[]{new IndentationChar('*', 0)}, 0);

        assertThat(actual)
                .isEqualTo("*");
    }

    @Test
    void getStringWhenTwoCharactersAndOneINdentation() {
        String actual = sut.getString(new IndentationChar[]{new IndentationChar('*', 1), new IndentationChar('s', 0)}, 1);

        assertThat(actual)
                .isEqualTo("""
                         *
                        s s
                         *""");
    }

    @Test
    void getIndentationWhenThreeCharacters() {
        IndentationChar[] actual = sut.calculateIndentation(new char[]{'*', 's', 'd'});

        assertThat(actual)
                .containsExactly(new IndentationChar('*', 2), new IndentationChar('s', 1), new IndentationChar('d', 0));
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

        private static String getLowerPartString(IndentationChar[] indentationChars, int maxIndentation) {
            StringBuilder result = new StringBuilder();
            for (int i = indentationChars.length - 2; i >= 0; i--) {
                IndentationChar indentationChar = indentationChars[i];
                result.append(" ".repeat(indentationChar.indentation))
                        .append(indentationChar.character)
                ;
                if (indentationChar.indentation != maxIndentation) {
                    result.append(" ".repeat(2 * maxIndentation - 2 * indentationChar.indentation - 1))
                            .append(indentationChar.character);
                }
                result.append("\n");
            }
            return result.toString();
        }

        private static String getUpperPartString(IndentationChar[] indentationChars, int maxIndentation) {
            StringBuilder result = new StringBuilder();
            for (IndentationChar indentationChar : indentationChars) {
                result.append(" ".repeat(indentationChar.indentation))
                        .append(indentationChar.character);
                if (indentationChar.indentation != maxIndentation) {
                    result.append(" ".repeat(2 * maxIndentation - 2 * indentationChar.indentation - 1))
                            .append(indentationChar.character);
                }
                result.append("\n");
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

        private char validate(char seed) {
            // if seed is not a letter
            if (!Character.isLetter(seed)) {
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

        String getString(IndentationChar[] indentationChars, int maxIndentation) {
            String firstPart = getUpperPartString(indentationChars, maxIndentation);
            String secondPart = getLowerPartString(indentationChars, maxIndentation);
            String fullDiamond = concat(firstPart, secondPart);
            return removeLastNewLine(fullDiamond);
        }

        IndentationChar[] calculateIndentation(char[] characters) {
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
            IndentationChar[] indentationChars = calculateIndentation(characters);
            int maxIndentation = calculateMaxIndentation(indentationChars);
            String diamond = getString(indentationChars, maxIndentation);
            return diamond;
        }

    }
}
