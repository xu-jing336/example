package com.example.xujing.brush_questions.controller.user.vo;

import com.example.xujing.brush_questions.common.utils.ToolUtils;
import com.example.xujing.brush_questions.controller.common.BaseVO;
import com.example.xujing.brush_questions.controller.exception.ParamErrorException;
import lombok.Data;

@Data
public class EnrollUserVO extends BaseVO {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

    @Override
    public void checkParam() throws ParamErrorException {

        if(ToolUtils.isEmpty(this.getUsername())){
            throw new ParamErrorException(400,"用户名不能为空");
        }
        if(ToolUtils.isEmpty(this.getPassword())){
            throw new ParamErrorException(400,"密码不能为空");
        }

        // 用户名不能超过20位，切格式为...


    }
}
