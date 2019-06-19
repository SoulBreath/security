package com.spring.security.mapper;

import com.spring.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    SysUser findByName(String name);
}
