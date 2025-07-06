package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("auto_maker")
@ApiModel(value = "AutoMaker对象", description = "")
public class AutoMaker implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("厂商id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("厂商名称")
    private String name;

    @ApiModelProperty("排序字母")
    private String orderLetter;

    @ApiModelProperty("是否删除 0未删除 1删除")
    private Boolean deleted;
}
