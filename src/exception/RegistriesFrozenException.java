package exception;

public class RegistriesFrozenException extends RuntimeException {
    public RegistriesFrozenException() {
        super("Registries are already frozen");
    }
}
