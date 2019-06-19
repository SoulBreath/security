package com.spring.security.mapper;

import com.spring.security.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {

     List<SysMenu> findAll();
     List<SysMenu> findByUserId(Long userId);
}
