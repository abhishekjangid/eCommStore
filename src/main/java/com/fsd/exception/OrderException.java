package com.fsd.exception;

public class OrderException extends Exception {
    private String code ;
    private String message;

    public OrderException(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
