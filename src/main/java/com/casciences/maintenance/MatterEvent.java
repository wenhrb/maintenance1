package com.casciences.maintenance;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lijie
 * @date 2020-09-23 01:36
 */
@Component
public class MatterEvent {

    /**
     * 每小时执行一次
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    private void startEvent(){
        System.out.println("开始检查");
    }
    
    
}
