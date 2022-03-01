package com.example.xujing.brush_questions.controller.common.handler;

import com.example.xujing.brush_questions.controller.common.BaseResponseVO;
import com.example.xujing.brush_questions.controller.exception.ParamErrorException;
import com.example.xujing.brush_questions.controller.exception.PractiseException;
import com.example.xujing.brush_questions.service.common.exception.CommonServiceExcetion;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PractiseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponseVO nextFilmException(PractiseException e){
        return BaseResponseVO.serviceFailed(e.getMessage());
    }

    @ExceptionHandler(CommonServiceExcetion.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponseVO commonServiceException(CommonServiceExcetion e){
        return BaseResponseVO.serviceFailed(e.getCode(),e.getErrMsg());
    }

    @ExceptionHandler(ParamErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponseVO paramErrorException(ParamErrorException e){
        return BaseResponseVO.serviceFailed(e.getCode(),e.getErrMsg());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponseVO exception(ExceptionHandler e){
        return BaseResponseVO.systemError();
    }
}
