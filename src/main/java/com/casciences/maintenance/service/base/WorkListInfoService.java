package com.casciences.maintenance.service.base;

import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.TaskListInfo;
import com.casciences.maintenance.entity.WorkListInfo;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * (WorkListInfo)表服务接口
 *
 * @author makejava
 * @since 2020-09-13 22:06:17
 */
public interface WorkListInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param workId 主键
     * @return 实例对象
     */
    WorkListInfo queryById(Integer workId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WorkListInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param workListInfo 实例对象
     * @return 实例对象
     */
    WorkListInfo insert(WorkListInfo workListInfo);

    /**
     * 修改数据
     *
     * @param workListInfo 实例对象
     * @return 实例对象
     */
    WorkListInfo update(WorkListInfo workListInfo);

    /**
     * 通过主键删除数据
     *
     * @param workId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer workId);

    /**
     * 创建工单
     * @param matters 需要维护事项
     * @return
     */
    void createWorkInfo(List<Matter> matters);


    /**
     * 根据用户Id 和 状态查询所有工单
     *
     * @param workerId
     * @param states
     * @return
     */
    List<WorkListInfo> queryByUserAndState(Integer workerId,List<Integer> states);

    /**
     * 工单确认
     * @param work
     * @param confirmTaskIds
     * @param skipTaskIds
     */
    void confirmWorkListInfo(int work,List<Integer> confirmTaskIds,List<Integer> skipTaskIds) throws Exception;
}