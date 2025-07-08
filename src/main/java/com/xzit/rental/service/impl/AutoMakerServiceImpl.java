package com.xzit.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzit.rental.entity.AutoMaker;
import com.xzit.rental.mapper.AutoMakerMapper;
import com.xzit.rental.service.IAutoMakerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2025-07-04
 */
@Service
public class AutoMakerServiceImpl extends ServiceImpl<AutoMakerMapper, AutoMaker> implements IAutoMakerService {

    @Override
    public Page<AutoMaker> search(int start, int size, AutoMaker autoMaker) {
        Page<AutoMaker> page = new Page<>(start, size);
        QueryWrapper<AutoMaker> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_letter")
                .like(StrUtil.isNotEmpty(autoMaker.getName()), "name", autoMaker.getName());
        this.page(page,queryWrapper);
        return page;
    }
}
