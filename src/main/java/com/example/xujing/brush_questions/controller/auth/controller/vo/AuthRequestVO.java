package com.example.xujing.brush_questions.controller.auth.controller.vo;

import com.example.xujing.brush_questions.controller.common.BaseVO;
import com.example.xujing.brush_questions.controller.exception.ParamErrorException;
import lombok.Data;

@Data
public class AuthRequestVO extends BaseVO {

    private String username;
    private String password;

    @Override
    public void checkParam() throws ParamErrorException {
        // TODO 验证过程补充
    }
}
