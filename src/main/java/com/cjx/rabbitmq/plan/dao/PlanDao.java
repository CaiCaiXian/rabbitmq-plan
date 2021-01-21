package com.cjx.rabbitmq.plan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjx.rabbitmq.plan.dto.PlanDTO;
import com.cjx.rabbitmq.plan.entity.PlanEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
*@Description plan操作dao
*@Verson v1.0.0
*@Author cjunxian
*@Date
*/
@Mapper
public interface PlanDao extends BaseMapper<PlanEntity> {

    /**
     * 根据id获取计划详情
     * @param id
     * @return
     */
    PlanDTO selectPlanById(@Param("id") Long id);

    /**
     * 获取版本号
     * @param id
     * @return
     */
    Integer selectVersion(@Param("id") Long id);


}
