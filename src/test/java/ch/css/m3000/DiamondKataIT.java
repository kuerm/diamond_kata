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
}
