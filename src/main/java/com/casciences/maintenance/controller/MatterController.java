package com.casciences.maintenance.controller;

import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.MatterService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
     * @param pageSize 每页展示的条数
     * @param pageNum  页数
     * @return 单条数据
     */
    @GetMapping("queryMatters")
    @ResponseBody
    @ApiOperation(value = "分页查询matter信息value", notes = "分页查询matter信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "每页展示的条数", name = "pageSize", dataType = "int"),
            @ApiImplicitParam(value = "页数", name = "pageNum", dataType = "int")})
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    public String queryMatters(@RequestParam int pageNum, @RequestParam int pageSize) {
        try {
            int offset = pageNum >= 1 ? (pageNum - 1) * pageSize : 0;
            List<Matter> matterList = matterService.queryAllByLimit(offset, pageSize);
            return BackMessage.successMessage(matterList);
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }

    }


    /**
     * 通过主键查询单条数据
     *
     * @param triggerId 每页展示的条数
     * @return 单条数据
     */
    @ApiOperation(value = "单条查询事件", notes = "单条查询事件")
    @ApiImplicitParam(value = "事件信息", name = "matter", dataType = "Matter<<ArrayList>>", paramType = "body",
            example = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })

    @GetMapping("queryMattersByTrigger")
    @ResponseBody
    public String queryMattersByTriggerIds(@RequestParam int triggerId) {
        List<Matter> matterList = matterService.queryMatterByTrigger(Lists.newArrayList(triggerId));
        return BackMessage.successMessage(matterList);
    }


    /**
     * 添加一个matter
     *
     * @param matter
     * @return
     */
    @ApiOperation(value = "增加一个事件", notes = "增加一个事件")
    @ApiImplicitParam(value = "事件信息", name = "matter", dataType = "Matter<<ArrayList>>", paramType = "body",
            example = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
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
    @ApiImplicitParam(value = "事件信息", name = "matter", dataType = "Matter<<ArrayList>>", paramType = "body",
            example = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
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
    @ApiImplicitParam(value = "事件信息", name = "matter", dataType = "Matter<<ArrayList>>", paramType = "body",
            example = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
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