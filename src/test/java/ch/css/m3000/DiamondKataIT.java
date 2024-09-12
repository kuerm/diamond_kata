package ch.css.m3000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiamondKataIT {
    @Test
    void diamondWhenWrongInputSeedThenReturnInvalidInputAsString() {
        DiamondKata diamondKata = new DiamondKata();

        String actual = diamondKata.diamond(0);

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
}
