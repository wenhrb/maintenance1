package com.casciences.maintenance.controller;

import com.casciences.maintenance.entity.EquipInfo;
import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.WorkerInfo;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.EquipInfoService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (EquipInfo)表控制层
 *
 * @author makejava
 * @since 2020-09-13 22:04:40
 */
@Controller
@Api(value = "equipInfo", tags = "装备信息接口")
@RequestMapping("equipInfo")
public class EquipInfoController {
    /**
     * 服务对象
     */
    @Resource
    private EquipInfoService equipInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "查询装备", notes = "查询装备")
    @ApiImplicitParam(value = "装备信息", name = "equipInfo", dataType = "EquipInfo<<ArrayList>>", paramType = "body",
            example = "{\"equipId\":\"1\",\"equipName\":\"test1\",\"partName\":\"test1_1\",\"partType\":1,\"state\":\"影响施工安全\",\"workerType\":1}")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })

    @GetMapping("selectOne")
    @ResponseBody
    public EquipInfo selectOne(Integer id) {
        return this.equipInfoService.queryById(id);
    }

    /**
     * 新增一个装备
     *
     * @param equipInfo
     * @return
     */
    @ApiOperation(value = "新增一个装备", notes = "新增一个装备")
    @ApiImplicitParam(value = "装备信息", name = "equipInfo", dataType = "EquipInfo<<ArrayList>>", paramType = "body",
            example = "{\"equipId\":\"1\",\"equipName\":\"test1\",\"partName\":\"test1_1\",\"partType\":1,\"state\":\"影响施工安全\",\"workerType\":1}")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addEquip(@RequestBody EquipInfo equipInfo) {
        try {
            equipInfoService.insert(equipInfo);
            return BackMessage.successMessage("success");
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }

    }

    @ApiOperation(value = "删除一个装备", notes = "删除一个装备")
    @ApiImplicitParam(value = "装备信息", name = "equipInfo", dataType = "EquipInfo<<ArrayList>>", paramType = "body",
            example = "{\"equipId\":\"1\",\"equipName\":\"test1\",\"partName\":\"test1_1\",\"partType\":1,\"state\":\"影响施工安全\",\"workerType\":1}")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @RequestMapping(value = "delEquip", method = RequestMethod.GET)
    @ResponseBody
    public String delEquip(@RequestParam int equipId) {
        try {
            equipInfoService.deleteById(equipId);
            return BackMessage.successMessage("success");
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }
    }

    @ApiOperation(value = "更改一个装备", notes = "更改一个装备")
    @ApiImplicitParam(value = "装备信息", name = "equipInfo", dataType = "EquipInfo<<ArrayList>>", paramType = "body",
            example = "{\"equipId\":\"1\",\"equipName\":\"test1\",\"partName\":\"test1_1\",\"partType\":1,\"state\":\"影响施工安全\",\"workerType\":1}")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @RequestMapping(value = "updateEquip", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateEquip(@RequestBody EquipInfo equipInfo) {
        try {
            equipInfoService.update(equipInfo);
            return BackMessage.successMessage("success");
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }
    }
}