package site.easy.to.build.crm.exception;

import java.util.List;

public class ImportException extends Exception {

    private List<String> errors;
    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public ImportException(String message) {
        super(message);
    }

    public ImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImportException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
    
}
