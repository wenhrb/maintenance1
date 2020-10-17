package com.casciences.maintenance.controller;

import com.casciences.maintenance.entity.EquipWorkInfo;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.EquipWorkInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * (EquipWorkInfo)表控制层
 *
 * @author makejava
 * @since 2020-09-13 22:05:32
 */
@Slf4j
@RestController
@RequestMapping("equipWorkInfo")
public class EquipWorkInfoController {
    /**
     * 服务对象
     */
    @Resource
    private EquipWorkInfoService equipWorkInfoService;

    /**
     * 启动设备接口
     *
     * @return 单条数据
     */
    @GetMapping("start")
    public String startEquip() {
        try {
            List<EquipWorkInfo> equipWorkInfos = equipWorkInfoService.queryAllByLimit(0, 1);
            if (CollectionUtils.isEmpty(equipWorkInfos)) {
                return BackMessage.successMessage("");
            }
            EquipWorkInfo equipWorkInfo = equipWorkInfos.get(0);
            equipWorkInfo.setEquipStartTime(new Date());
            equipWorkInfoService.update(equipWorkInfo);
            return BackMessage.successMessage("ok");
        } catch (Exception e) {
            log.error("启动接口异常，e:{}", e);
            return BackMessage.errorMessage("失败");
        }
    }

    /**
     * 关闭设备接口
     *
     * @return 单条数据
     */
    @GetMapping("end")
    public String endEquip() {
        try {
            List<EquipWorkInfo> equipWorkInfos = equipWorkInfoService.queryAllByLimit(0, 1);
            if (CollectionUtils.isEmpty(equipWorkInfos)) {
                return BackMessage.successMessage("");
            }
            EquipWorkInfo equipWorkInfo = equipWorkInfos.get(0);
            equipWorkInfo.setEquipLastShutdownTime(new Date());
            equipWorkInfoService.update(equipWorkInfo);
            return BackMessage.successMessage("ok");
        } catch (Exception e) {
            log.error("关闭接口异常，e:{}", e);
            return BackMessage.errorMessage(e.getCause());
        }

    }

}