package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.WorkerInfoDao;
import com.casciences.maintenance.entity.WorkerInfo;
import com.casciences.maintenance.service.base.WorkerInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * (WorkerInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:06:29
 */
@Service("workerInfoService")
public class WorkerInfoServiceImpl implements WorkerInfoService {
    @Resource
    private WorkerInfoDao workerInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workerId 主键
     * @return 实例对象
     */
    @Override
    public WorkerInfo queryById(Integer workerId) {
        return workerInfoDao.queryById(workerId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<WorkerInfo> queryAllByLimit(int offset, int limit) {
        return workerInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param workerInfo 实例对象
     * @return 实例对象
     */
    @Override
    public WorkerInfo insert(WorkerInfo workerInfo) {
        workerInfoDao.insert(workerInfo);
        return workerInfo;
    }

    /**
     * 修改数据
     *
     * @param workerInfo 实例对象
     * @return 实例对象
     */
    @Override
    public WorkerInfo update(WorkerInfo workerInfo) throws Exception {
        if (null == workerInfoDao.queryById(workerInfo.getWorkerId())) {
            throw new Exception("员工不存在");
        }
        workerInfoDao.update(workerInfo);
        return queryById(workerInfo.getWorkerId());
    }

    /**
     * 通过主键删除数据
     *
     * @param workerId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer workerId) {
        return workerInfoDao.deleteById(workerId) > 0;
    }

    @Override
    public List<WorkerInfo> queryWorkersByCondition(WorkerInfo workerInfo) throws Exception {
        if (workerInfo == null) {
            throw new Exception("条件不正确");
        }
        return workerInfoDao.queryAll(workerInfo);
    }
}