package com.example.xujing.brush_questions.controller.common;


import com.example.xujing.brush_questions.controller.exception.ParamErrorException;

public abstract class BaseVO {


    public abstract void checkParam() throws ParamErrorException;

}
