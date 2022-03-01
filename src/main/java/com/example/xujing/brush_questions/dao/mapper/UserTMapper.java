package com.example.xujing.brush_questions.dao.mapper;

import com.example.xujing.brush_questions.dao.entity.UserT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xujing.brush_questions.example.dao.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xujing
 * @since 2022-02-25
 */
public interface UserTMapper extends BaseMapper<UserT> {
    User findByName(String name);

}
