package com.cjx.rabbitmq.plan.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.rabbitmq.plan.dto.PlanDTO;

/**
*@Description 计划服务层
*@Verson v1.0.0
*@Author cjunxian
*@Date
*/
public interface PlanService {

    /**
    *@Description 通知用户
    *@Verson v1.0.0
    *@Author cjunxian
    *@Date
    */
    void notifyUser(Long planId,Integer version);


    /**
     * 保存或更新计划
     * @param planDTO
     */
    boolean saveOrUpdatePlan(PlanDTO planDTO);


    /**
     * 删除计划
     */
    boolean deletePlan(Long planId);
}
