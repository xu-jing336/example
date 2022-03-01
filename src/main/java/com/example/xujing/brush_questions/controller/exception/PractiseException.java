package com.example.xujing.brush_questions.controller.exception;

public class PractiseException extends Exception {
    private Integer code;
    private String errMsg;

    public PractiseException(int code, String errMsg){
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
