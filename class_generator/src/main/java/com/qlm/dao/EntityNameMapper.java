package com.qlm.dao;

import com.qlm.entity.EntityName;

public interface EntityNameMapper {
    int insert(EntityName record);

    int insertSelective(EntityName record);
}