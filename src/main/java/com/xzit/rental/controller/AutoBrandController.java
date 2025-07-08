package com.xzit.rental.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzit.rental.entity.AutoBrand;
import com.xzit.rental.service.IAutoBrandService;
import com.xzit.rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author teacher_shi
 * @since 2025-07-04
 */
@RestController
@RequestMapping("/rental/autoBrand")
public class AutoBrandController {

    @Resource
    private IAutoBrandService autoBrandService;

    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable int start, @PathVariable int size, @RequestBody AutoBrand autoBrand){
        Page<AutoBrand> page = new Page<>(start,size);
        return Result.success().setData(autoBrandService.searchByPage(page,autoBrand));
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids){
        List<Integer> list = Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        return autoBrandService.removeByIds(list)? Result.success() : Result.fail();
    }
    @PostMapping
    public Result save(@RequestBody AutoBrand autoBrand){
        return autoBrandService.save(autoBrand)? Result.success() : Result.fail();
    }
    @PutMapping
    public Result update(@RequestBody AutoBrand autoBrand){
        return autoBrandService.updateById(autoBrand)? Result.success() : Result.fail();
    }
}
