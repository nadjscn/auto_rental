package com.xzit.rental.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class RouteVo {
    private String path;
    private String component;
    private String name;
    private Boolean alwaysShow;
    private Mete mete;
    private List<RouteVo> children;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Mete{
        private String title;
        private String icon;
        private Object[] roles;
    }
}
