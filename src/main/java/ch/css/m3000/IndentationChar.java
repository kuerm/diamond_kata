package ch.css.m3000;

import java.util.Objects;

public record IndentationChar(char character,
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