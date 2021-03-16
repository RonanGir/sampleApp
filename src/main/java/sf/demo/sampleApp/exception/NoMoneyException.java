package sf.demo.sampleApp.exception;

public class NoMoneyException extends RuntimeException {

    public NoMoneyException(String msg) {
        super(msg);
    }
}
