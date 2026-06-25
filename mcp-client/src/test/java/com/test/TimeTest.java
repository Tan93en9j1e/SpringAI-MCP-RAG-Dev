package com.test;

import cn.hutool.core.date.StopWatch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.test
 * ClassName: TimeTest
 * Author: tmj
 * Date: 2026/6/25 15:15
 * version: 1.0
 * Description:
 */
@SpringBootTest(classes = Appendable.class)
public class TimeTest {

    @Test
    public void testTime() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("task1");
        Thread.sleep(1000);
        stopWatch.stop();

        stopWatch.start("task2");
        Thread.sleep(300);
        stopWatch.stop();

        stopWatch.start("task3");
        Thread.sleep(100);
        stopWatch.stop();

        //打印任务的耗时统计
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.shortSummary());

        //任务总览
        System.out.println("Total Time: " + stopWatch.getTotalTimeMillis());
        System.out.println("Count: " + stopWatch.getTaskCount());
    }

}
