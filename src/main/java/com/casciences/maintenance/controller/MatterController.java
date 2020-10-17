package com.casciences.maintenance.controller;

import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.WorkerInfo;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.MatterService;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Matter)表控制层
 *
 * @author makejava
 * @since 2020-09-13 22:05:42
 */
@Api(value = "matter", tags = "事件维护接口")
@RestController
@RequestMapping("matter")
public class MatterController {
    /**
     * 服务对象
     */
    @Resource
    private MatterService matterService;

    /**
     * 通过主键查询单条数据
     *
     * @param triggerId 每页展示的条数
     * @return 单条数据
     */
    @ApiOperation(value = "查询一个事件", notes = "查询一个事件")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ApiParam(value = "事件信息", name = "matter", defaultValue = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}")


    @GetMapping("queryMattersByTrigger")
    @ResponseBody
    public String queryMattersByTriggerIds(@RequestParam int triggerId) {
        List<Matter> matterList = matterService.queryMatterByTrigger(Lists.newArrayList(triggerId));
        return BackMessage.successMessage(matterList);
    }

    /**
     * 根据条件查询员工信息
     *
     * @param matter 每页展示的条数
     * @return 单条数据
     */
    @RequestMapping(value = "queryMatters", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "根据条件查询事件信息", notes = "传入对象")
    @ApiParam(name = "matterInfo", value = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}", required = true)
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    public String queryMatters(@RequestBody Matter matter) {
        try {

            List<Matter> matters = matterService.queryMatterByCondition(matter);
            return BackMessage.successMessage(matters);
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }

    }


    /**
     * 添加一个matter
     *
     * @param matter
     * @return
     */

    @ApiOperation(value = "新增一个事件", notes = "新增一个事件")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ApiParam(value = "事件信息", name = "matter", defaultValue = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}")


    @RequestMapping(value = "addMatters", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addMatters(@RequestBody Matter matter) {
        matterService.insert(matter);
        return BackMessage.successMessage("success");
    }

    /**
     * 删除一个matter
     *
     * @param matterIds
     * @return
     */
    @ApiOperation(value = "删除一个事件", notes = "删除一个事件")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ApiParam(value = "事件信息", name = "matter", defaultValue = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}")

    @RequestMapping(value = "deleteMatters", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteMatters(@RequestBody List<Integer> matterIds) {
        try {
            matterService.deleteByIds(matterIds);
            return BackMessage.successMessage("success");
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }


    }

    /**
     * 修改matter
     * <p>
     * <p>
     * old : {"matterId":1,"equipId":1,"partId":2,"preOp":"保养"}
     * new : {"matterId":1,"equipId":2,"partId":1,"preOp":"1保养"}
     *
     * @param matter
     * @return
     */
    @ApiOperation(value = "更改一个事件", notes = "更改一个事件")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ApiParam(value = "事件信息", name = "matter", defaultValue = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}")

    @RequestMapping(value = "updateMatter", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateMatters(@RequestBody Matter matter) {
        try {
            if (matter.getMatterId() == null) {
                return BackMessage.errorMessage("matter不合法");
            }
            matterService.update(matter);
            return BackMessage.successMessage("success");
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }
    }
}