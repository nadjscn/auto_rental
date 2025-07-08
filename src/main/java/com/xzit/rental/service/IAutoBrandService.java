package com.xzit.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzit.rental.entity.AutoBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2025-07-04
 */
public interface IAutoBrandService extends IService<AutoBrand> {
    Page<AutoBrand> searchByPage(Page<AutoBrand> page, AutoBrand autoBrand);

    List<AutoBrand> selectByMakerId(Integer makerId);
}
