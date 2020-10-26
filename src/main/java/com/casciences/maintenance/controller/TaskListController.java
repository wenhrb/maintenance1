package com.casciences.maintenance.controller;

import com.casciences.maintenance.entity.TaskListInfo;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.TaskListInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lijie
 * @date 2020-10-26 15:29
 */
@Api(tags = "任务项相关接口")
@Slf4j
@RestController
@RequestMapping("taskList")
public class TaskListController {

    @Autowired
    private TaskListInfoService taskListInfoService;


    @GetMapping("findTaskItemByWorkId")
    @ApiOperation(value = "通过工单Id 查询，工单下所有任务项")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "工单Id", name = "workId", dataType = "int", example = "0")})
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ResponseBody
    public String findTaskItemByWorkId(int workId) {
        try {
            List<TaskListInfo> taskListInfos = taskListInfoService.queryByWorkId(workId);
            return BackMessage.successMessage(taskListInfos);
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getCause());
        }

    }
}
