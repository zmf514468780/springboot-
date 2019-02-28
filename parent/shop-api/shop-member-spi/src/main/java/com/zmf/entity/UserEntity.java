package com.zmf.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 11:46
 * @Description:
 */
@Getter
@Setter
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;
    private String openid;
}
