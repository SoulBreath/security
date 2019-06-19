package com.spring.security.entity;

import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author xuyang
 * @version 1.0
 * @date 2019/6/19 9:45
 */
@Data
public class SysRole {

    private Long id;
    private String name;

    /* 扩展属性 */
    private List<SysMenu> menus;
}