package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author teacher_shi
 * @since 2025-07-04
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户账户")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("账户是否过	期")
    private boolean isAccountNonExpired=true;

    @ApiModelProperty("账户是否被锁	定")
    private boolean isAccountNonLocked=true;

    @ApiModelProperty("密码是否	过期")
    private boolean isCredentialsNonExpired=true;

    @ApiModelProperty("账户是否可用")
    private boolean isEnabled=true;

    @ApiModelProperty("用户真实姓名")
    private String realname;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("所属部门id")
    private Integer deptId;

    @ApiModelProperty("所属部门名称")
    private String deptName;

    @ApiModelProperty("性别")
    private Boolean gender;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String emial;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("是否管理员")
    private Boolean isAdmin;

    @ApiModelProperty("创建时间")
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    public Collection<? extends GrantedAuthority> authorities;

    @TableField(exist = false)
    private List<Permission> permissionList;
}
