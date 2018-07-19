public class EmptyTextFieldException extends Exception {
    String message;
    public void EmptyTextFieldException()
    {
        this.message="The text is empty No text Found or Extracted";
    }
}
