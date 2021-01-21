package com.cjx.rabbitmq.plan.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
*@Description 计划表示层
*@Verson v1.0.0
*@Author cjunxian
*@Date
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO implements Serializable {

    /**
     * 序列化版本 UID.
     */
    private static final long serialVersionUID=1L;

    /**
     * 唯一标识
     * 需要序列化，否则会丢失精度
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    /**
     * 类型id
     */
    private Integer typeId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 地点
     */
    private String location;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 是否生效
     */
    private Integer status = 0;

    /**
     * 类型名
     */
    private String planType;
}
