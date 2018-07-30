public class EmptyDictionaryException extends Exception {
    String message;
    public void EmptyDictionaryException()
    {
        this.message="the dictionry is Empty or the text is not extracted";
    }
}
