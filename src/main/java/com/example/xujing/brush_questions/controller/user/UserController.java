package com.example.xujing.brush_questions.controller.user;

import com.example.xujing.brush_questions.common.utils.ToolUtils;
import com.example.xujing.brush_questions.controller.common.BaseResponseVO;
import com.example.xujing.brush_questions.controller.common.TraceUtil;
import com.example.xujing.brush_questions.controller.exception.ParamErrorException;
import com.example.xujing.brush_questions.controller.exception.PractiseException;
import com.example.xujing.brush_questions.controller.user.vo.EnrollUserVO;
import com.example.xujing.brush_questions.controller.user.vo.UserInfoVO;
import com.example.xujing.brush_questions.service.common.exception.CommonServiceExcetion;
import com.example.xujing.brush_questions.service.user.UserServiceAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/user/")
@Api("用户模块相关的 API")
public class UserController {

    @Resource
    private UserServiceAPI userServiceAPI;

    @ApiOperation(value = "用户名重复性验证",notes = "用户名重复性验证")
    @ApiImplicitParam(name = "username",
            value = "待验证的用户名称",
            paramType = "query", required = true, dataType = "string")
    @RequestMapping(value = "check",method = RequestMethod.POST)
    public BaseResponseVO checkUser(String username) throws CommonServiceExcetion, PractiseException {
        if(ToolUtils.isEmpty(username)){
            throw new PractiseException(1,"username不能为空");
        }
        boolean hasUserName = userServiceAPI.checkUserName(username);
        if(hasUserName){
            return BaseResponseVO.serviceFailed("用户名已存在");
        }else{
            return BaseResponseVO.success();
        }
    }

    @ApiOperation(value = "用户信息注册",notes = "用户名、密码和手机号必须填写")
    @ApiImplicitParam(name = "enrollUserVO",
            value = "用户登记注册信息实体", required = true, dataType = "EnrollUserVO")
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public BaseResponseVO register(@RequestBody EnrollUserVO enrollUserVO) throws CommonServiceExcetion, ParamErrorException {
        // 领域模型 贫血模型 - 充血模型
        enrollUserVO.checkParam();

        userServiceAPI.userEnroll(enrollUserVO);

        return BaseResponseVO.success();
    }

    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    public BaseResponseVO describeUserInfo() throws CommonServiceExcetion, ParamErrorException {

        String userId = TraceUtil.getUserId();

        UserInfoVO userInfoVO = userServiceAPI.describeUserInfo(userId);

        userInfoVO.checkParam();

        return BaseResponseVO.success(userInfoVO);
    }


    @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    public BaseResponseVO updateUserInfo(@RequestBody UserInfoVO userInfoVO) throws CommonServiceExcetion, ParamErrorException {

        userInfoVO.checkParam();

        UserInfoVO result = userServiceAPI.updateUserInfo(userInfoVO);

        userInfoVO.checkParam();

        return BaseResponseVO.success(result);
    }

    @RequestMapping(value = "logout",method = RequestMethod.POST)
    public BaseResponseVO logout() throws CommonServiceExcetion, ParamErrorException {

        String userId = TraceUtil.getUserId();

        /*
            1、用户信息放入Redis缓存
            2、去掉用户缓存
         */

        return BaseResponseVO.success();
    }

}
