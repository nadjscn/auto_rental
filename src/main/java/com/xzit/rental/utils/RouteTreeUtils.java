package com.xzit.rental.utils;

import com.xzit.rental.entity.Permission;
import com.xzit.rental.vo.RouteVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteTreeUtils {
    public static List<RouteVo> buildRouteTree(List<Permission> permissionList,int pid) {
        // 初始化路由VO列表
List<RouteVo> routeVOList = new ArrayList<>();

// 使用Optional处理可能为空的权限列表，确保空列表时不会抛出空指针异常
Optional.ofNullable(permissionList).orElse(new ArrayList<>())
        // 过滤出父ID匹配的权限项
        .stream().filter(permission -> permission!=null && permission.getPid()==pid)
        .forEach(permission -> {
            // 创建路由VO对象
            RouteVo routeVo = new RouteVo();
            // 设置路由路径
            routeVo.setPath(permission.getRoutePath());
            // 设置路由名称
            routeVo.setName(permission.getRouteName());
            // 判断是否为根路径
            if (permission.getPid()==0){//根路径
                // 根路径设置为Layout组件，且始终显示
                routeVo.setComponent("Layout");
                routeVo.setAlwaysShow(true);
            }else{
                // 非根路径使用权限的路由URL作为组件，不始终显示
                routeVo.setComponent(permission.getRouteUrl());
                routeVo.setAlwaysShow(false);
            }
            // 设置路由元数据
            routeVo.setMeta(routeVo.new Meta(permission.getPermissionLable(),
                    permission.getIcon(),
                    permission.getPermissionCode().split(",")));
            // 递归构建子路由
            List<RouteVo> children = buildRouteTree(permissionList,permission.getId());
            // 设置子路由
            routeVo.setChildren(children);
            // 将构建好的路由添加到列表中
            routeVOList.add(routeVo);
        });
// 返回构建好的路由列表
return routeVOList;

    }
}
