package com.casciences.maintenance.controller;

import com.alibaba.fastjson.JSON;
import com.casciences.maintenance.entity.WorkListInfo;
import com.casciences.maintenance.entity.WorkerInfo;
import com.casciences.maintenance.enums.QualityEnum;
import com.casciences.maintenance.enums.WorkStateEnum;
import com.casciences.maintenance.enums.WorkerStateEnum;
import com.casciences.maintenance.enums.WorkerType;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.WorkListInfoService;
import com.casciences.maintenance.service.base.WorkerInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WorkListInfo)表控制层
 *
 * @author makejava
 * @since 2020-09-13 22:06:17
 */
@Api(tags = "工单相关接口")
@Slf4j
@RestController
@RequestMapping("workListInfo")
public class WorkListInfoController {
    /**
     * 服务对象
     */
    @Resource
    private WorkListInfoService workListInfoService;
    @Resource
    private WorkerInfoService workerInfoService;

    /**
     * 根据用户身份获取工单列表
     *
     * @param workerId 工人Id
     * @return
     */
    @GetMapping("getUserWorkList")
    @ApiOperation(value = "获取员工的工单列表", notes = "获取员工的工单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "员工Id", name = "workerId", dataType = "int", example = "0")})
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ResponseBody
    public String getAllWorkList(int workerId) {
        try {
            WorkerInfo workerInfo = workerInfoService.queryById(workerId);
            if (workerInfo == null) {
                return BackMessage.errorMessage("工人不存在,请确认工人Id" + workerId);
            }
            List<WorkListInfo> workListInfoList;
            if (workerInfo.getWorkerType() == WorkerType.ADMIN.getValue() || workerInfo.getWorkerType() == WorkerType.CON_ADMIN.getValue()) {
                workListInfoList = workListInfoService.queryByUserAndState(null, WorkStateEnum.getWorkAllowStates());
            } else {
                workListInfoList = workListInfoService.queryByUserAndState(workerId, WorkStateEnum.getWorkAllowStates());
            }

            return BackMessage.successMessage(JSON.toJSON(workListInfoList));
        } catch (Exception e) {
            log.error("获取工单列表失败,e:{}", e);
            return BackMessage.errorMessage("获取工单列表失败");
        }

    }


    /**
     * 指派工单给工人
     *
     * @param workListId 工单Id
     * @param workerId   工人Id
     * @return
     */
    @GetMapping("appointWorker")
    @ApiOperation(value = "工单指派给特定工人", notes = "工单指派给特定工人")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "工单Id", name = "workListId", dataType = "int", example = "0"),
            @ApiImplicitParam(value = "员工Id", name = "workerId", dataType = "int", example = "0")})
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ResponseBody
    public String appointWorker(int workListId, int workerId) {
        try {
            WorkListInfo workListInfo = workListInfoService.queryById(workListId);
            if (workListInfo == null) {
                return BackMessage.errorMessage("工单不存在,请确认工单" + workListId);
            }
            WorkerInfo workerInfo = workerInfoService.queryById(workerId);
            if (workerInfo == null) {
                return BackMessage.errorMessage("工人不存在,请确认工人Id" + workerId);
            }
            workListInfo.setWorker(workerId);
            workListInfoService.update(workListInfo);
            return BackMessage.errorMessage("指派成功");
        } catch (Exception e) {
            log.error("指派工单失败,e:{}", e);
            return BackMessage.errorMessage("指派工单失败");
        }
    }


    @RequestMapping(value = "confirmWorkList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "提交工单", notes = "提交工单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "跳过的项目Id", name = "skipTaskIds", dataType = "list", paramType = "body"),
            @ApiImplicitParam(value = "确认的项目Id", name = "confirmTaskIds", dataType = "list", paramType = "body"),
            @ApiImplicitParam(value = "员工Id", name = "workerId", dataType = "int", paramType = "body")})
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })


    @ResponseBody
    public String confirmWorkList(int work, List<Integer> confirmTaskIds, List<Integer> skipTaskIds) {
        try {
            workListInfoService.confirmWorkListInfo(work, confirmTaskIds, skipTaskIds);
            return BackMessage.successMessage("成功");
        } catch (Exception e) {
            log.error("", e);
            return BackMessage.errorMessage("失败");
        }
    }


    @RequestMapping(value = "ratingQuality", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "工单评分", notes = "工单评分")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "工单Id", name = "workListId", dataType = "list", paramType = "query"),
            @ApiImplicitParam(value = "评分", name = "quality", dataType = "int", paramType = "query", example = "0")})
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ResponseBody
    public String ratingQuality(int workListId, int quality) {
        try {
            workListInfoService.ratingQuality(workListId, quality);
            return BackMessage.successMessage("成功");
        } catch (Exception e) {
            log.error("", e);
            return BackMessage.errorMessage(e.getMessage());
        }
    }

    @GetMapping("queryAllQuality")
    @ApiOperation(value = "获取评分信息")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    public String queryAllQuality() {
        return BackMessage.successMessage(QualityEnum.getDescMessage());
    }

}