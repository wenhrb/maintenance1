package com.casciences.maintenance.service.work.impl;

import com.casciences.maintenance.entity.EquipWorkInfo;
import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.Trigger;
import com.casciences.maintenance.entity.WorkListInfo;
import com.casciences.maintenance.enums.TriggerTypeEnums;
import com.casciences.maintenance.service.base.EquipWorkInfoService;
import com.casciences.maintenance.service.base.MatterService;
import com.casciences.maintenance.service.base.TriggerService;
import com.casciences.maintenance.service.base.WorkListInfoService;
import com.casciences.maintenance.service.work.WorkExecuteService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijie
 * @date 2020-09-26 10:34
 */
@Service("workExecuteService")
@Slf4j
public class WorkExecuteServiceImpl implements WorkExecuteService {

    private static final long ONE_HOUR_TIME = 3600 * 6000;
    @Resource
    private MatterService matterService;

    @Resource
    private EquipWorkInfoService equipWorkInfoService;

    @Resource
    private TriggerService triggerService;

    @Resource
    private WorkListInfoService workListInfoService;

    @Override
    public List<Matter> findNeedExecuteMatters() {
        List<EquipWorkInfo> equipWorkInfos = equipWorkInfoService.queryAllByLimit(0, 1);
        if (CollectionUtils.isEmpty(equipWorkInfos)) {
            log.warn("还没有录入装备运行信息，请添加启动信息");
            return Lists.newArrayList();
        }
        //todo 确定时间间隔 是从启动时间算还是 上一次关闭时间算
        Date equipStartTime = equipWorkInfos.get(0).getEquipStartTime();
        //四舍五入取整
        int startHours = Math.round((System.currentTimeMillis() - equipStartTime.getTime()) / ONE_HOUR_TIME);
        log.info("设备一共运行了{}小时了", startHours);
        List<Trigger> triggers = triggerService.queryTimeTriggerByType(TriggerTypeEnums.TIME_SPAN);
        List<Integer> triggerIds= triggers.stream().filter(m -> startHours % m.getTimeSpan() == 0).map(Trigger::getTriggerId).collect(Collectors.toList());

       List<Matter> matters =  matterService.queryMatterByTrigger(triggerIds);
       return matters;
    }
}
