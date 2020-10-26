package com.casciences.maintenance.controller;

import com.casciences.maintenance.entity.EquipInfo;
import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.WorkerInfo;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.EquipInfoService;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        return equipInfoService.queryById(id);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param equipInfo 主键
     * @return 单条数据
     */
    @ApiOperation(value = "查询对象")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @GetMapping("queryEquipInfo")
    @ResponseBody
    public String queryEquip(@ApiParam(value = "equipInfo") @RequestParam EquipInfo equipInfo) {
        try {
            List<EquipInfo> equipInfoList = equipInfoService.queryByEquipInfo(equipInfo);
            return BackMessage.successMessage(equipInfoList);
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }

    }

    /**
     * 通过装备Id查询所有子对象
     *
     * @param equipId 装备Id
     * @return 单条数据
     */
    @ApiOperation(value = "通过装备Id查询所有子对象")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @GetMapping("queryPartInfoByEquipId")
    @ResponseBody
    public String queryPartInfoByEquipId(@ApiParam(value = "equipId" ,example = "0") @RequestParam int equipId) {
        try {
            List<EquipInfo> equipInfoList = equipInfoService.queryPartInfoByEquipId(equipId);
            return BackMessage.successMessage(equipInfoList);
        } catch (Exception e) {
            return BackMessage.errorMessage(e.getMessage());
        }
    }


    /**
     * 新增一个装备
     *
     * @param equipInfo
     * @return
     */
    @ApiOperation(value = "新增一个装备", notes = "新增一个装备")
    @ApiImplicitParam(value = "装备信息", name = "equipInfo", dataType = "EquipInfo<<ArrayList>>", paramType = "body",
            example = "{\"equipId\":1,\"equipName\":\"test1\",\"partName\":\"test1_1\",\"partType\":1,\"state\":\"影响施工安全\",\"workerType\":1}")
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
            example = "{\"equipId\":1,\"equipName\":\"test1\",\"partName\":\"test1_1\",\"partType\":1,\"state\":\"影响施工安全\",\"workerType\":1}")
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