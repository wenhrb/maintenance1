package com.casciences.maintenance.service.base;

import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.WorkerInfo;
import javafx.concurrent.Worker;

import java.util.List;

/**
 * (WorkerInfo)表服务接口
 *
 * @author makejava
 * @since 2020-09-13 22:06:29
 */
public interface WorkerInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param workerId 主键
     * @return 实例对象
     */
    WorkerInfo queryById(Integer workerId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WorkerInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param workerInfo 实例对象
     * @return 实例对象
     */
    WorkerInfo insert(WorkerInfo workerInfo);

    /**
     * 修改数据
     *
     * @param workerInfo 实例对象
     * @return 实例对象
     */
    WorkerInfo update(WorkerInfo workerInfo) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param workerId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer workerId);

    /**
     * 通过triggerId 查询所有的事件
     *
     * @param WorkerIds 定时任务的id
     * @return 对象列表
     */

//    List<WorkerInfo> queryWorkerInfoByWorker(List<Integer> WorkerIds);

    /**
     * 通过主键删除数据
     *
     * @param workerIds 主键
     * @return 是否成功
     */
//    boolean deleteByIds(List<Integer> workerIds) throws Exception;
}
