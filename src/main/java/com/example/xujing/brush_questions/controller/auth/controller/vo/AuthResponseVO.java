package com.example.xujing.brush_questions.controller.auth.controller.vo;


import com.example.xujing.brush_questions.controller.common.BaseVO;
import com.example.xujing.brush_questions.controller.exception.ParamErrorException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseVO extends BaseVO {

    private String randomKey;
    private String token;

    @Override
    public void checkParam() throws ParamErrorException {

    }
}
