package exception;

public class PaletteOutOfBoundsException extends RuntimeException {
    public PaletteOutOfBoundsException(int id, String paletteName) {
        super("Palette index out of range: " + id + " for " + paletteName);
    }
}
