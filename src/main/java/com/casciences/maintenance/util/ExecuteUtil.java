package com.casciences.maintenance.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 执行工具类
 *
 * @author lijie
 * @date 2020-09-26 10:15
 */
public class ExecuteUtil {

    /**
     * 判断是否可以执行该任务
     *
     * @param hours     执行阈值
     * @param startTime 设备开始运行时间
     * @return
     */
    private boolean isAllowExecuteWork(int hours, long startTime) {
        LocalDateTime startLocalDateTime = LocalDateTime.ofEpochSecond(startTime / 1000, 0, ZoneOffset.ofHours(8));
        Duration duration = Duration.between(LocalDateTime.now(), startLocalDateTime);
        return hours >= duration.toHours();
    }
}
