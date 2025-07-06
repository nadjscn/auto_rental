package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author teacher_shi
 * @since 2025-07-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("权限名称")
    private String permissionLable;

    @ApiModelProperty("父权限id")
    private Integer pid;

    @ApiModelProperty("父权限名称")
    private String parentLabel;

    @ApiModelProperty("权限标识")
    private String permissionCode;

    @ApiModelProperty("权限路由地址")
    private String routePath;

    @ApiModelProperty("权限路由名称")
    private String routeName;

    @ApiModelProperty("权限路径")
    private String routeUrl;

    @ApiModelProperty("权限类型 权限类型 0-根目录 1-子目录 2-按钮级别")
    private Integer permissionType;

    @ApiModelProperty("图标地址")
    private String icon;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("创建时间")
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)//序列化时，为空的属性不序列化
// 子权限
    private List<Permission> children;
}
