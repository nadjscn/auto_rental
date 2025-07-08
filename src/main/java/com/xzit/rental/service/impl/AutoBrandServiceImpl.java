package com.xzit.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzit.rental.entity.AutoBrand;
import com.xzit.rental.mapper.AutoBrandMapper;
import com.xzit.rental.service.IAutoBrandService;
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
public class AutoBrandServiceImpl extends ServiceImpl<AutoBrandMapper, AutoBrand> implements IAutoBrandService {
    @Override
    public Page<AutoBrand> searchByPage(Page<AutoBrand> page, AutoBrand autoBrand) {
        return baseMapper.searchByPage(page,autoBrand);
    }
    @Override
    public List<AutoBrand> selectByMakerId(Integer makerId) {
        QueryWrapper<AutoBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mid",makerId);
        return baseMapper.selectList(queryWrapper);
    }
}
