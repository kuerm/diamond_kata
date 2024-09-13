package ch.css.m3000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DiamondKataIT {
    private final DiamondKata sut = new DiamondKata();

    @Test
    void diamondWhenWrongInputSeedThenReturnInvalidInputAsString() {
        char invalidInput = '0';

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> sut.diamond(invalidInput));
    }

    @Test
    void diamondWhenInputSeedIsAThenReturnA() {
        String actual = sut.diamond('A');

        assertThat(actual).isEqualTo("A");
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


}
