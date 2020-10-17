package com.casciences.maintenance.controller;

import com.casciences.maintenance.entity.TaskListInfo;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.TaskListInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TaskListInfo)表控制层
 *
 * @author makejava
 * @since 2020-09-13 22:05:52
 */
@Slf4j
@RestController
@RequestMapping("taskListInfo")
public class TaskListInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TaskListInfoService taskListInfoService;



}