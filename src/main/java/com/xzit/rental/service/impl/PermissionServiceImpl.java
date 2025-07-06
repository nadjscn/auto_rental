package com.xzit.rental.service.impl;

import com.xzit.rental.entity.Permission;
import com.xzit.rental.mapper.PermissionMapper;
import com.xzit.rental.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2025-07-04
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> selectPermissionListByUserId(Integer userId) {
        return baseMapper.selectPermissionListByUserId(userId);
    }

}
