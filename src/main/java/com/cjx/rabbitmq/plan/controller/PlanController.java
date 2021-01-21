package com.cjx.rabbitmq.plan.controller;

import com.cjx.rabbitmq.plan.dto.PlanDTO;
import com.cjx.rabbitmq.plan.service.PlanService;
import com.cjx.rabbitmq.plan.util.CommonResult;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
*@Description
*@Verson v1.0.0
*@Author cjunxian
*@Date
*/
@RestController
@RequestMapping("/planManage")
public class PlanController {

    @Autowired
    PlanService planService;


    /**
     * 更新或保存计划
     * @param planDTO
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public CommonResult saveOrUpdate(@RequestBody PlanDTO planDTO){
        if(planService.saveOrUpdatePlan(planDTO)){
            return CommonResult.success("保存计划成功！");
        }else {
            return CommonResult.failed("保存计划失败！");
        }
    }

    /**
     * 删除计划
     * @param planId
     * @return
     */
    @DeleteMapping("delete/{planId}")
    public CommonResult delete(@PathVariable("planId") @NotNull Long planId){
        if(planService.deletePlan(planId)){
            return CommonResult.success("删除计划成功！");
        }else {
            return CommonResult.failed("删除计划失败");
        }
    }

}
