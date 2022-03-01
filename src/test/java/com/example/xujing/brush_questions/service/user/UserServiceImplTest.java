package com.example.xujing.brush_questions.service.user;

import com.example.xujing.brush_questions.controller.user.vo.EnrollUserVO;
import com.example.xujing.brush_questions.controller.user.vo.UserInfoVO;
import com.example.xujing.brush_questions.service.common.exception.CommonServiceExcetion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Resource
    private UserServiceAPI userServiceAPI;

    @Test
    public void userEnroll() throws CommonServiceExcetion {
        EnrollUserVO enrollUserVO = new EnrollUserVO();
        enrollUserVO.setUsername("1");
        enrollUserVO.setPassword("1");
        enrollUserVO.setAddress("1");
        enrollUserVO.setEmail("1");
        enrollUserVO.setPhone("1");

        userServiceAPI.userEnroll(enrollUserVO);

    }

    @Test
    public void checkUserName() {
    }

    @Test
    public void userAuth() {
    }

    @Test
    public void describeUserInfo() throws CommonServiceExcetion {
        String userId = "2";
        System.out.println(userServiceAPI.describeUserInfo(userId));
    }

    @Test
    public void updateUserInfo() throws CommonServiceExcetion {
        UserInfoVO userInfoVO = new UserInfoVO();
        System.out.println(userInfoVO);
        userInfoVO.setUuid(1);
        userInfoVO.setUsername("next");
        userInfoVO.setLifeState("0");

        System.out.println(userServiceAPI.updateUserInfo(userInfoVO));
    }

    @Test
    public void login() {

    }
}