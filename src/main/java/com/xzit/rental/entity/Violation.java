package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("busi_violation")
@ApiModel(value = "Violation对象", description = "")
public class Violation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("违章id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("车辆id")
    private Integer autoId;

    @ApiModelProperty("违章时间")
    private LocalDateTime violationTime;

    @ApiModelProperty("违章事由")
    private String reason;

    @ApiModelProperty("违章地点")
    private String location;

    @ApiModelProperty("扣分")
    private Integer deductPoints;

    @ApiModelProperty("罚款")
    private Integer fine;

    @ApiModelProperty("是否处理 0 -未处理 1-已处理")
    private Boolean status;

    @ApiModelProperty("创建时间")
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    private Boolean deleted;
}
