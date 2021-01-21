package com.cjx.rabbitmq.plan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjx.rabbitmq.plan.entity.PlanEntity;
import org.apache.ibatis.annotations.Mapper;

/**
*@Description plantype操作dao
*@Verson v1.0.0
*@Author cjunxian
*@Date
*/
@Mapper
public interface PlanTypeDao extends BaseMapper<PlanEntity> {
}
