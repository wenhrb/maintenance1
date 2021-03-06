package com.casciences.maintenance.service.base.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casciences.maintenance.dao.MatterDao;
import com.casciences.maintenance.entity.EquipInfo;
import com.casciences.maintenance.entity.EquipMapping;
import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.MatterTrigger;
import com.casciences.maintenance.enums.EquipType;
import com.casciences.maintenance.enums.WorkerType;
import com.casciences.maintenance.excelFile.ExcelFile;
import com.casciences.maintenance.excelFile.FileType;
import com.casciences.maintenance.model.MatterExcel;
import com.casciences.maintenance.service.base.EquipInfoService;
import com.casciences.maintenance.service.base.EquipMappingService;
import com.casciences.maintenance.service.base.MatterService;
import com.casciences.maintenance.service.base.MatterTriggerService;
import com.casciences.maintenance.service.file.FileDownLoadService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.tools.javac.util.Assert;
import io.swagger.models.auth.In;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * (Matter)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:05:41
 */
@Service("matterService")
public class MatterServiceImpl implements MatterService {

    @Resource
    private EquipInfoService equipInfoService;


    @Resource
    private EquipMappingService equipMappingService;


    @Resource
    private MatterDao matterDao;

    @Resource
    private MatterTriggerService matterTriggerService;

    @Resource
    private FileDownLoadService fileDownLoadService;

    /**
     * 通过ID查询单条数据
     *
     * @param matterId 主键
     * @return 实例对象
     */
    @Override
    public Matter queryById(Integer matterId) {
        return matterDao.queryById(matterId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Matter> queryAllByLimit(int offset, int limit) {
        return matterDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param matter 实例对象
     * @return 实例对象
     */
    @Override
    public Matter insert(Matter matter) {
        matterDao.insert(matter);
        return matter;
    }

    /**
     * 修改数据
     *
     * @param matter 实例对象
     * @return 实例对象
     */
    @Override
    public Matter update(Matter matter) throws Exception {
        Matter matter1 = queryById(matter.getMatterId());
        if (matter1 == null) {
            throw new Exception("matter not exists,matterId:" + matter.getMatterId());
        }
        matterDao.update(matter);
        return queryById(matter.getMatterId());
    }

    /**
     * 通过主键删除数据
     *
     * @param matterId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer matterId) {
        return matterDao.deleteById(matterId) > 0;
    }

    @Override
    public boolean deleteByIds(List<Integer> matterIds) throws Exception {
        for (Integer matterId : matterIds) {
            matterDao.deleteById(matterId);
        }
        return true;
    }


    /**
     * 数据存入数据库
     *
     * @param jsonArray
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDataToDd(JSONArray jsonArray) throws Exception{
        Matter matter = new Matter();
        List<MatterExcel> matterExcels = jsonArray.toJavaList(MatterExcel.class);
        for (MatterExcel matterExcel : matterExcels) {
            String partStr = matterExcel.getPart();
            String equipStr = matterExcel.getEquip();
            String state = matterExcel.getState();
            Assert.check(StringUtils.isEmpty(equipStr),"对象名称为空");
            Assert.check(StringUtils.isEmpty(partStr),"子对象名称为空");
            Assert.check(StringUtils.isEmpty(state),"阈值为空");

            EquipInfo equipInfo = new EquipInfo();
            equipInfo.setEquipName(equipStr);
            equipInfo.setPartName(partStr);
            equipInfo.setState(state);
            equipInfo.setPartType(EquipType.PART.getType());
            WorkerType workerType  =WorkerType.NORMAL;
            if(equipStr.contains("电")){
                workerType =WorkerType.ELECTRICITY;
            }
            equipInfo.setWorkerType(workerType.getValue());
            List<EquipInfo>  parts = equipInfoService.queryByEquipInfo(equipInfo);
            if (CollectionUtils.isEmpty(parts)) {
                equipInfoService.insert(equipInfo);
            }

            matter.setEquipId(equipInfo.getEquipId());
            //todo 需要确定 要不要把对象和子对象放到一个表中
            matter.setPartId(0);
            matter.setExecuStandard(matterExcel.getState());
            matter.setPreOp(matterExcel.getPreOp());
            //todo 需要确定 上传的文件要不要放入trigger
            matter.setMatterTriggerId(0);
            matter.setWorkerType(workerType.getValue());
            matterDao.insert(matter);
        }

    }

    @Override
    public ByteArrayOutputStream findAllMatterExcel(Matter matter) throws Exception {
        List<Matter> matters = queryMatterByCondition(matter);
        if (CollectionUtils.isEmpty(matters)) {
            throw new Exception("没有找到数据");
        }
        List<EquipInfo> equipInfos = equipInfoService.queryAllByLimit(0, Integer.MAX_VALUE);
        List<MatterTrigger> triggers = matterTriggerService.queryAllByLimit(0, Integer.MAX_VALUE);
        Map<Integer, String> equipName = Maps.newHashMap(), partName = Maps.newHashMap(), triggerName = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(equipInfos)) {
            equipName = equipInfos.stream().filter(m -> EquipType.EQUIP.getType().equals(m.getPartType())).collect(Collectors.toMap(EquipInfo::getEquipId, EquipInfo::getEquipName, (k1, k2) -> k1));
            partName = equipInfos.stream().filter(m -> EquipType.PART.getType().equals(m.getPartType())).collect(Collectors.toMap(EquipInfo::getEquipId, EquipInfo::getPartName, (k1, k2) -> k1));
        }
        if (!CollectionUtils.isEmpty(triggers)) {
            triggerName = triggers.stream().collect(Collectors.toMap(MatterTrigger::getTriggerId, MatterTrigger::getDescription, (k1, k2) -> k1));
        }
        List<MatterExcel> matterExcels = Lists.newArrayList();
        for (Matter tempMatter : matters) {
            MatterExcel matterExcel = new MatterExcel();
            matterExcel.setEquipId(tempMatter.getEquipId());
            matterExcel.setPart(partName.get(tempMatter.getPartId()));
            matterExcel.setEquip(equipName.get(tempMatter.getEquipId()));
            matterExcel.setMatterTrigger(triggerName.get(tempMatter.getMatterTriggerId()));
            matterExcel.setPreOp(tempMatter.getPreOp());
            matterExcel.setWorkerType(tempMatter.getWorkerType());
            matterExcel.setState(tempMatter.getExecuStandard());
            matterExcels.add(matterExcel);
        }
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(matterExcels));
        jsonObject.put("datas", jsonArray);
        return fileDownLoadService.exportFile(new ExcelFile(FileType.matter.getType()), jsonObject);
    }

    @Override
    public List<Matter> queryMatterByTrigger(List<Integer> triggerIds) {
        return matterDao.queryMatterByTrigger(triggerIds);
    }

    @Override
    public List<Matter> queryMatterByCondition(Matter matter) throws Exception {
        return matterDao.queryAll(matter);
    }
}