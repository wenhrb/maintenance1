package com.casciences.maintenance.service.work;

import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.TaskListInfo;
import com.casciences.maintenance.entity.WorkListInfo;
import com.casciences.maintenance.service.base.WorkListInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lijie
 * @date 2020-09-26 10:38
 */
@Component
@Slf4j
@EnableScheduling

public class WorkScheduledService {

    @Resource
    private WorkExecuteService workExecuteService;

    @Resource
    private WorkListInfoService workListInfoService;

    /**
     * 每小时执行一次
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    private void startEvent() {
        System.out.println("开始检查");
        List<Matter> matterList = workExecuteService.findNeedExecuteMatters();
        if (CollectionUtils.isEmpty(matterList)) {
            log.info("没有发现需要执行的任务");
            return;
        }
        try {
            Map<Integer,List<Matter>> integerListMap = matterList.stream().collect(Collectors.groupingBy(Matter::getMatterTriggerId));
            for(Map.Entry<Integer,List<Matter>> item:integerListMap.entrySet()){
               workListInfoService.createWorkInfo(
                       item.getValue());
            }
        } catch (Exception e) {
            log.error("自动生成工单失败，需要执行的任务有{}", matterList.size());
        }
    }
}
