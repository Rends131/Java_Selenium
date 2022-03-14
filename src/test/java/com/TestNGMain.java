package com;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class TestNGMain {

    // 打包后的 testNG 启动入口
    public static void main(String[] args) {
        TestNG tng = new TestNG();
        List<String> suites = Lists.newArrayList();
        //添加要执行的testng.xml文件
        suites.add("testng.xml");
        tng.setTestSuites(suites);
        tng.run();

    }
}
