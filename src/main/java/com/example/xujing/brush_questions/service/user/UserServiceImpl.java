package com.example.xujing.brush_questions.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.xujing.brush_questions.common.utils.MD5Util;
import com.example.xujing.brush_questions.common.utils.ToolUtils;
import com.example.xujing.brush_questions.controller.user.vo.EnrollUserVO;
import com.example.xujing.brush_questions.controller.user.vo.UserInfoVO;
import com.example.xujing.brush_questions.dao.entity.StudentT;
import com.example.xujing.brush_questions.dao.mapper.StudentTMapper;

import com.example.xujing.brush_questions.dao.mapper.UserTMapper;
import com.example.xujing.brush_questions.example.dao.entity.User;
import com.example.xujing.brush_questions.service.common.exception.CommonServiceExcetion;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserServiceAPI{

    @Resource
    private StudentTMapper studentTMapper;


    @Override
    public void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceExcetion {

        // EnrollUserVO -> NextUserT 转换
        StudentT nextUserT = new StudentT();
        // email  adress  phone复制
        BeanUtils.copyProperties(enrollUserVO,nextUserT);
        nextUserT.setUserName(enrollUserVO.getUsername());
        nextUserT.setUserPwd(MD5Util.encrypt(enrollUserVO.getPassword()));

        // 数据插入
        int isSuccess = studentTMapper.insert(nextUserT);

        // 判断插入是否成功
        if(isSuccess!=1){
            throw new CommonServiceExcetion(501,"用户登记失败！");
        }

    }

    @Override
    public boolean checkUserName(String userName) throws CommonServiceExcetion {

//        Optional.ofNullable(userName).orElseThrow(CommonServiceExcetion::new);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);

        int hasUserName = studentTMapper.selectCount(queryWrapper);

        return hasUserName == 0 ? false : true;
    }

    @Override
    public boolean userAuth(String userName, String userPwd) throws CommonServiceExcetion {
        if(ToolUtils.isEmpty(userName) || ToolUtils.isEmpty(userPwd)){
            throw new CommonServiceExcetion(400,"用户失败失败！");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        // 1、判断用户名是否存在
        List<StudentT> list = studentTMapper.selectList(queryWrapper);
        if(list.size() == 0){
            return false;
        }else {
            // 2、如果存在，则判断密码是否正确
            StudentT studentT = list.get(0);
            // 3、对用户输入的密码进行MD5加密，然后判断两个密码是否相等
            if(MD5Util.encrypt(userPwd).equals(studentT.getUserPwd())){
                return true;
            }
        }
        return false;
    }

    @Override
    public UserInfoVO describeUserInfo(String userId) throws CommonServiceExcetion {
        StudentT studentT = studentTMapper.selectById(userId);
        if(studentT != null && studentT.getUuid() != null){
            UserInfoVO userInfoVO = user2InfoVO(studentT);
            return userInfoVO;
        }else{
            throw new CommonServiceExcetion(404,"用户编号为["+userId+"]的用户不存在");
        }
    }

    @Override
    public UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws CommonServiceExcetion {
        StudentT studentT = info2user(userInfoVO);
        System.out.println(studentT);
        if(studentT != null && studentT.getUuid() != null){
            int isSuccess = studentTMapper.updateById(studentT);
            if(isSuccess==1){
                UserInfoVO result = describeUserInfo(userInfoVO.getUuid()+"");
                return result;
            }else{
                throw new CommonServiceExcetion(500,"用户信息修改失败");
            }
        }else{
            throw new CommonServiceExcetion(404,"用户编号为["+userInfoVO.getUuid()+"]的用户不存在");
        }
    }


    /*
    --------------------------------以下都是自定义方法--------------------------------------------
     */
    private UserInfoVO user2InfoVO(StudentT studentT){
        UserInfoVO userInfoVO = new UserInfoVO();

        userInfoVO.setUsername(studentT.getUserName());
        userInfoVO.setNickname(studentT.getNickName());

        userInfoVO.setBeginTime(studentT.getBeginTime().toEpochSecond(ZoneOffset.of("+8")));
        userInfoVO.setUpdateTime(studentT.getUpdateTime().toEpochSecond(ZoneOffset.of("+8")));

        userInfoVO.setLifeState(studentT.getLifeState()+"");

        BeanUtils.copyProperties(studentT,userInfoVO);

        return userInfoVO;
    }

    private StudentT info2user(UserInfoVO userInfoVO){
        StudentT studentT = new StudentT();

        studentT.setUserName(userInfoVO.getUsername());
        studentT.setNickName(userInfoVO.getNickname());

        studentT.setUpdateTime(LocalDateTime.now());
        // 最好是用正则表达式判断是否为数字类型之后再转换
        if(Optional.ofNullable(userInfoVO.getLifeState()).isPresent()){
            studentT.setLifeState(Integer.parseInt(userInfoVO.getLifeState()));
        }

        BeanUtils.copyProperties(userInfoVO,studentT);

        return studentT;
    }

}
