package org.ninjaware.notebook.model.system;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private int status;
    private List<String> errors = new ArrayList<>();

    public ErrorResponse() {
    }

    public ErrorResponse(int status) {
        this.status = status;
    }

    public ErrorResponse(int status, String error) {
        this(status);
        addError(error);
    }

    public ErrorResponse(int status, List<String> errors) {
        this(status);
        this.errors = errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
