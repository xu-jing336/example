package com.example.xujing.brush_questions.dao.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author xujing
 * @since 2022-02-25
 */
public class UserT extends Model<UserT> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    private String userName;

    private String userPwd;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UserT{" +
        ", uuid=" + uuid +
        ", userName=" + userName +
        ", userPwd=" + userPwd +
        "}";
    }
}
