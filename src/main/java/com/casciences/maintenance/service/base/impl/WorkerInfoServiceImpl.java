package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.WorkerInfoDao;
import com.casciences.maintenance.entity.WorkerInfo;
import com.casciences.maintenance.service.base.WorkerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        return this.workerInfoDao.queryById(workerId);
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
        return this.workerInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param workerInfo 实例对象
     * @return 实例对象
     */
    @Override
    public WorkerInfo insert(WorkerInfo workerInfo) {
        this.workerInfoDao.insert(workerInfo);
        return workerInfo;
    }

    /**
     * 修改数据
     *
     * @param workerInfo 实例对象
     * @return 实例对象
     */
    @Override
    public WorkerInfo update(WorkerInfo workerInfo) throws Exception{
        if(null == workerInfoDao.queryById(workerInfo.getWorkerId()) ){
            throw new Exception("员工不存在");
        }
        this.workerInfoDao.update(workerInfo);
        return this.queryById(workerInfo.getWorkerId());
    }

    /**
     * 通过主键删除数据
     *
     * @param workerId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer workerId) {
        return this.workerInfoDao.deleteById(workerId) > 0;
    }
}