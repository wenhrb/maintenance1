package com.casciences.maintenance.service.base.impl;

import com.alibaba.fastjson.JSONArray;
import com.casciences.maintenance.dao.MatterDao;
import com.casciences.maintenance.entity.EquipInfo;
import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.enums.EquipType;
import com.casciences.maintenance.model.MatterExcel;
import com.casciences.maintenance.service.base.EquipInfoService;
import com.casciences.maintenance.service.base.MatterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    private MatterDao matterDao;

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
       for(Integer matterId :matterIds){
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
    public void saveDataToDd(JSONArray jsonArray) {
        Matter matter = new Matter();
        List<MatterExcel> matterExcels = jsonArray.toJavaList(MatterExcel.class);
        for (MatterExcel matterExcel : matterExcels) {
            String partStr = matterExcel.getPart();
            String equipStr = matterExcel.getEquip();

            EquipInfo part = equipInfoService.queryEquipOrPartByName(partStr, EquipType.PART);
            if (part == null) {
                part = equipInfoService.insert(new EquipInfo(partStr, Strings.EMPTY, EquipType.PART.getType(), Strings.EMPTY));
            }
            EquipInfo equip = equipInfoService.queryEquipOrPartByName(equipStr, EquipType.EQUIP);
            if (equip == null) {
                equip = new EquipInfo(equipStr, Strings.EMPTY, EquipType.EQUIP.getType(), Strings.EMPTY);
                equip.setWorkerType(matterExcel.getWorkerType());
                equip = equipInfoService.insert(new EquipInfo(equipStr, Strings.EMPTY, EquipType.EQUIP.getType(), Strings.EMPTY));
            }
            matter.setEquipId(equip.getEquipId());
            matter.setPartId(part.getEquipId());
            matter.setExecuStandard(matterExcel.getState());
            matter.setPreOp(matterExcel.getPreOp());
            matter.setMatterTriggerId(0);
            matter.setWorkerType(equip.getWorkerType());
            matterDao.insert(matter);
        }

    }

    @Override
    public List<Matter> queryMatterByTrigger(List<Integer> triggerIds) {
        return null;
    }
}