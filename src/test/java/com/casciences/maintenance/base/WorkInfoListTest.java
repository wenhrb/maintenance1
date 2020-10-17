package com.casciences.maintenance.base;

import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.WorkListInfo;
import com.casciences.maintenance.service.base.MatterService;
import com.casciences.maintenance.service.base.WorkListInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkInfoListTest {


    @Autowired
    private WorkListInfoService workListInfoService;


    @Autowired
    private MatterService matterService;

    @Test
    public void testCreateWorkInfoList() {
        List<Matter> matterList = matterService.queryAllByLimit(0, 100);
        System.out.println(matterList.size());
        workListInfoService.createWorkInfo(matterList);
        List<WorkListInfo> workListInfos =  workListInfoService.queryAllByLimit(0,100);
        workListInfos.stream().forEach(m->{
            System.out.println(m);
        });
    }

}
