package com.casciences.maintenance.base;

import com.casciences.maintenance.entity.EquipInfo;
import com.casciences.maintenance.service.base.EquipInfoService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author lijie
 * @date 2020-09-13 22:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EquipTest {

    @Autowired
    private EquipInfoService equipInfoService;

    @Test
    public void testSelectEquipInfo() {
        List<EquipInfo> equipInfos = equipInfoService.queryAllByLimit(0, 10);
        Optional.ofNullable(equipInfos).orElse(Lists.newArrayList()).stream().forEach(m -> {
            System.out.println(m.toString());
        });
    }

    @Test
    public void testTe() {
        System.out.println("开始检查");
    }
}
