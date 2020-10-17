package com.casciences.maintenance.controller;

import com.casciences.maintenance.entity.WorkerInfo;
import com.casciences.maintenance.enums.WorkerStateEnum;
import com.casciences.maintenance.enums.WorkerType;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.WorkerInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WorkerInfo)表控制层
 *
 * @author makejava
 * @since 2020-09-13 22:06:29
 */
@Slf4j
@Api(value = "matter", tags = "工人信息接口")
@RestController
@RequestMapping("workerInfo")
public class WorkerInfoController {
    /**
     * 服务对象
     */
    @Resource
    private WorkerInfoService workerInfoService;


    /**
     * 工人上线
     *
     * @return
     */
    @GetMapping("upState")
    @ApiOperation(value = "修改员工状态", notes = "修改员工状态")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "员工Id", name = "workerId", dataType = "int"),
            @ApiImplicitParam(value = "员工状态（0，1，2）", name = "state", dataType = "int")})
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ResponseBody
    public String workerUpdateState(int workerId, int state) {
        try {
            WorkerInfo workerInfo = workerInfoService.queryById(workerId);
            if (workerInfo == null) {
                return BackMessage.errorMessage("工人" + workerId + "不存在");
            } else {
                workerInfo.setWorkerState(WorkerStateEnum.getInstance(state).getValue());
                workerInfoService.update(workerInfo);
            }
            log.info("修改用户状态，员工：{},状态码：{}", workerInfo.getWorkerId(), state);
            return BackMessage.successMessage("员工上线成功");
        } catch (Exception e) {
            log.info("修改用户状态失败，员工：{},状态码：{}", workerId, state);
            return BackMessage.errorMessage("员工上线失败");
        }
    }

    /**
     * 新增一个工人
     *
     * @param workerInfo
     * @return
     */
    @ApiOperation(value = "新增一个工人", notes = "新增一个工人")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ApiParam(value = "工人信息", name = "workerInfo", defaultValue = "{\"workerName\":\"test1\",\"workerType\":1,\"workerState\":1,\"remark\":\"wqwre\"}")
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public String addWorker(@RequestBody WorkerInfo workerInfo) {
        try {
            workerInfoService.insert(workerInfo);
            return BackMessage.successMessage("success");
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }

    }


    @ApiOperation(value = "删除一个工人", notes = "删除一个工人")
    @ApiParam(value = "员工Id", name = "workerId")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @RequestMapping(value = "delWorker", method = RequestMethod.GET)
    @ResponseBody
    public String delWorker(@RequestParam int workerId) {
        try {
            workerInfoService.deleteById(workerId);
            return BackMessage.successMessage("success");
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }
    }


    @ApiOperation(value = "修改一个工人", notes = "修改一个工人")
    @ApiParam(value = "工人信息", name = "workerInfo",
            defaultValue = "{\"workerId\":\"1\",\"workerName\":\"test1\",\"workerType\":1,\"workerState\":1,\"remark\":\"wqwre\"}")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @RequestMapping(value = "updateWorker", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateWorker(@RequestBody WorkerInfo workerInfo) {
        try {
            workerInfoService.update(workerInfo);
            return BackMessage.successMessage("success");
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }
    }


    /**
     * 分页查询员工信息
     *
     * @param workerInfo 每页展示的条数
     * @return 单条数据
     */
    @GetMapping("queryWorkers")
    @ResponseBody
    @ApiOperation(value = "根据条件查询员工信息", notes = "传入对象")
    @ApiParam(name = "workerInfo", value = "{\"workerName\":\"test1\",\"workerType\":1,\"workerState\":1}", required = true)
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    public String queryMatters(@RequestBody WorkerInfo workerInfo) {
        try {

            List<WorkerInfo> workerInfos = workerInfoService.queryWorkersByCondition(workerInfo);
            return BackMessage.successMessage(workerInfos);
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }
    }

    @GetMapping("queryAllWorkType")
    @ApiOperation(value = "获取工种类型")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    public String queryAllWorkType() {
        return BackMessage.successMessage(WorkerType.getDescMessage());
    }


}