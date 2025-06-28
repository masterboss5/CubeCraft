package exception;

public class JsonParsingException extends RuntimeException {
    public JsonParsingException(LoadingContext context, String fileName) {
        super("JSON parsing error for " + context.getText() + " file" + " | " + fileName);
    }

    public enum LoadingContext {
        MODEL("model");

        private String text;

        LoadingContext(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
