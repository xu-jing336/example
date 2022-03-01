package com.example.xujing.brush_questions.controller.auth.controller;

import com.example.xujing.brush_questions.controller.auth.controller.vo.AuthRequestVO;
import com.example.xujing.brush_questions.controller.auth.controller.vo.AuthResponseVO;
import com.example.xujing.brush_questions.controller.auth.util.JwtTokenUtil;
import com.example.xujing.brush_questions.controller.common.BaseResponseVO;
import com.example.xujing.brush_questions.controller.exception.ParamErrorException;
import com.example.xujing.brush_questions.service.common.exception.CommonServiceExcetion;
import com.example.xujing.brush_questions.service.user.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserServiceAPI userServiceAPI;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public BaseResponseVO auth(@RequestBody AuthRequestVO authRequestVO) throws ParamErrorException, CommonServiceExcetion {

        authRequestVO.checkParam();

        boolean isValid = userServiceAPI.userAuth(authRequestVO.getUsername()
                , authRequestVO.getPassword());

        if(isValid){
            String randomKey = jwtTokenUtil.getRandomKey();
            String token = jwtTokenUtil.generateToken(authRequestVO.getUsername(),randomKey);

            AuthResponseVO authResponseVO = AuthResponseVO.builder()
                    .randomKey(randomKey)
                    .token(token).build();

            return BaseResponseVO.success(authResponseVO);
        }else{
            return BaseResponseVO.serviceFailed(1,"用户名或密码不正确！！");
        }

    }

}
