package com.cjx.rabbitmq.plan.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjx.rabbitmq.plan.config.DelayMQConfig;
import com.cjx.rabbitmq.plan.constant.PlanConstant;
import com.cjx.rabbitmq.plan.dao.PlanDao;
import com.cjx.rabbitmq.plan.dto.PlanDTO;
import com.cjx.rabbitmq.plan.entity.PlanEntity;
import com.cjx.rabbitmq.plan.service.PlanService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@Description 计划服务层实现类
 *@Verson v1.0.0
 *@Author cjunxian
 *@Date
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanDao, PlanEntity> implements PlanService {

    /**
     * 计划dao
     */
    @Autowired
    PlanDao planDao;

    /**
     * rabbitmq template
     */
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void notifyUser(Long planId,Integer version) {
        PlanDTO planDTO = planDao.selectPlanById(planId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());
        //保证计划是生效的且和通知时是同一个版本
        if(planDTO != null && PlanConstant.CAN_USE.equals(planDTO.getStatus()) && planDTO.getVersion().equals(version)){
            //发送通知
            System.out.println(now + " 你消息提醒："+planDTO.toString());
        }else{
            System.out.println(now + " " + planDTO.getTitle()+": 该消息已取消提醒");
        }
    }

    @Override
    public boolean saveOrUpdatePlan(PlanDTO planDTO) {
        PlanEntity planEntity = new PlanEntity();
        //dto拷贝给数据库操作实体
        BeanUtil.copyProperties(planDTO,planEntity);
        try {
            //判断是更新还是新增
            Long id = planEntity.getId();
            if(ObjectUtil.isNotNull(id)){
                //更新,将数据库的版本号加一
                Integer old = planDao.selectVersion(id);
                planEntity.setVersion(old + 1);
                updateById(planEntity);
            }else {
                //新增 初始化版本号
                planEntity.setVersion(0);
                planEntity.setCreateTime(new Date());
                save(planEntity);
            }
            //获取数据库中最新数据
            PlanDTO newPlanDTO = planDao.selectPlanById(planEntity.getId());
            //判断开始时间是不是比当前时间大且消息提醒是生效
            long x = (planDTO.getStartTime().getTime() - System.currentTimeMillis());
            if( x >= 0 && planDTO.getStatus().equals(PlanConstant.CAN_USE)){
                //计划加入消息队列
                rabbitTemplate.convertAndSend(DelayMQConfig.DELAY_EXCHANGE_NAME, DelayMQConfig.DELAY_ROUTE_KEY,newPlanDTO, msg->{
                    //设置延迟
                    msg.getMessageProperties().setDelay((int)x);
                    return msg;
                });
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePlan(Long planId) {
        if(planDao.deleteById(planId) > 0){
            return true;
        }else {
            return false;
        }
    }


}
