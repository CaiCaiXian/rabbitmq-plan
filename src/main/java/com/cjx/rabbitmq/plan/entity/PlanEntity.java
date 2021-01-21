package com.cjx.rabbitmq.plan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@TableName("qk_plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanEntity implements Serializable {

    /**
     * 序列化版本 UID.
     */
    private static final long serialVersionUID=1L;
    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;
    /**
     * 类型id
     */
    @TableField(value = "type_id")
    private Integer typeId;
    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;
    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;
    /**
     * 地点
     */
    @TableField(value = "location")
    private String location;
    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;
    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 版本号
     */
    @TableField(value = "version")
    private Integer version;
    /**
     * 是否生效
     */
    @TableField(value = "status")
    private Integer status;

}
