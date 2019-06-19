package com.spring.security.entity;

import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author xuyang
 * @version 1.0
 * @date 2019/6/19 9:44
 */
@Data
public class SysUser {

    private Long id;
    private String name;
    private String password;

    /* 扩展属性 */
    private List<SysRole> roles;
}