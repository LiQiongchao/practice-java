package com.oio.practice.framework.forest;

import com.oio.practice.PracticeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Liqc
 * @date 2021/3/12 11:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PracticeApplication.class)
public class AmapClientTest {

    // 注入接口实例
    @Autowired
    private AmapClient amapClient;

    @Test
    public void getLocation() {
        // 调用接口
        Map result = amapClient.getLocation("121.475078", "31.223577");
        System.out.println(result);
    }
}
