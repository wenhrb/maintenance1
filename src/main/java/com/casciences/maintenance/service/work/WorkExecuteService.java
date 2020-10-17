package com.casciences.maintenance.service.work;

import com.casciences.maintenance.entity.Matter;

import java.util.List;

/**
 * @author lijie
 * @date 2020-09-26 10:14
 */
public interface WorkExecuteService {

    /**
     * 找到需要执行的维护事项
     *
     * @return 需要执行的维护事项
     */
    List<Matter> findNeedExecuteMatters();
}
