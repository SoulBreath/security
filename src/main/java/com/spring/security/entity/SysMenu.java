package com.spring.security.entity;

import lombok.Data;

/**
 * Description:
 *
 * @author xuyang
 * @version 1.0
 * @date 2019/6/19 9:46
 */
@Data
public class SysMenu {

    private Long id;
    private Long pid;
    private String url;
    private String name;
}