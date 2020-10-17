package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.EquipInfoDao;
import com.casciences.maintenance.entity.EquipInfo;
import com.casciences.maintenance.entity.EquipMapping;
import com.casciences.maintenance.enums.EquipType;
import com.casciences.maintenance.service.base.EquipInfoService;
import com.casciences.maintenance.service.base.EquipMappingService;
import com.google.common.collect.Lists;
import com.sun.tools.javac.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (EquipInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:04:39
 */
@Service("equipInfoService")
public class EquipInfoServiceImpl implements EquipInfoService {
    @Resource
    private EquipInfoDao equipInfoDao;

    @Autowired
    private EquipMappingService equipMappingService;

    /**
     * 通过ID查询单条数据
     *
     * @param equipId 主键
     * @return 实例对象
     */
    @Override
    public EquipInfo queryById(Integer equipId) {
        return equipInfoDao.queryById(equipId);
    }

    @Override
    public List<EquipInfo> queryByEquipInfo(EquipInfo equipInfo) {
        if(equipInfo.getPartType() == null){
            equipInfo.setPartType(EquipType.EQUIP.getType());
        }
        return equipInfoDao.queryAll(equipInfo);
    }

    @Override
    public List<EquipInfo> queryPartInfoByEquipId(int equipId) throws Exception {
        EquipInfo equipInfo = new EquipInfo();
        equipInfo.setPartType(EquipType.PART.getType());
        List<EquipMapping> equipMappings = equipMappingService.queryMappingInfo(equipId);
        if(CollectionUtils.isEmpty(equipMappings)){
            throw new Exception("未找到对象与子对象对应关系");
        }
        return equipInfoDao.queryPartInfoByEquipIds(equipMappings.stream().map(EquipMapping::getEquipId).collect(Collectors.toList()));
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<EquipInfo> queryAllByLimit(int offset, int limit) {
        return equipInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param equipInfo 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public EquipInfo insert(EquipInfo equipInfo) throws Exception {
        if(queryByEquipInfo(equipInfo) !=null){
            throw new Exception("已经存在");
        }
        if(equipInfo.getPartType().equals(EquipType.PART.getType())){
            Assert.check(StringUtils.isEmpty(equipInfo.getEquipName()) && StringUtils.isEmpty(equipInfo.getPartName()),"添加对象，对象/子对象没有名称");
            Assert.check(StringUtils.isEmpty(equipInfo.getState()),"阈值不能为空");
            //拆分为对象和子对象分别插入
            EquipMapping equipMapping = new EquipMapping();
            EquipInfo equipObject = new EquipInfo();
            equipObject.setPartType(EquipType.EQUIP.getType());
            equipObject.setEquipName(equipInfo.getPartName());
            equipObject.setWorkerType(equipInfo.getWorkerType());
            List<EquipInfo> equipInfos = Lists.newArrayList(equipInfo,equipObject);
            equipMapping.setPartId(equipInfo.getEquipId());
            equipMapping.setEquipId(equipObject.getEquipId());
            equipInfoDao.batchInsert(equipInfos);
            equipMappingService.insert(equipMapping);
        }else {
            if(StringUtils.isEmpty(equipInfo.getEquipName())){
                throw new Exception("添加对象，对象没有名称");
            }
            equipInfo.setState("");
            equipInfoDao.insert(equipInfo);
        }
        return equipInfo;
    }

    /**
     *
     * @param equipInfo
     * @return
     */
    private void insertOne(EquipInfo equipInfo){
        equipInfoDao.insert(equipInfo);
    }

    /**
     * 修改数据
     *
     * @param equipInfo 实例对象
     * @return 实例对象
     */
    @Override
    public EquipInfo update(EquipInfo equipInfo) {
        equipInfoDao.update(equipInfo);
        return queryById(equipInfo.getEquipId());
    }

    /**
     * 通过主键删除数据
     *
     * @param equipId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer equipId) {
        return equipInfoDao.deleteById(equipId) > 0;
    }

    @Override
    public EquipInfo queryEquipOrPartByName(String name, EquipType type) {
        List<EquipInfo> equipInfos = Lists.newArrayList();
        if (EquipType.EQUIP == type) {
            equipInfos = equipInfoDao.queryEquipByName(name);
        } else {
            equipInfos = equipInfoDao.queryPartByName(name);
        }
        return CollectionUtils.isEmpty(equipInfos) ? null : equipInfos.get(0);
    }


}