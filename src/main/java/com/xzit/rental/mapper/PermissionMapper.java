package com.xzit.rental.mapper;

import com.xzit.rental.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author teacher_shi
 * @since 2025-07-04
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectPermissionListByUserId(Integer userId);
}
