package com.example.chapter2.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("歌手", ""));
        result.add(new TestData("林丹", ""));
        result.add(new TestData("飞天猪", ""));
        result.add(new TestData("燃灯者", ""));
        result.add(new TestData("嘉年华", ""));
        result.add(new TestData("三伏天", ""));
        result.add(new TestData("老虎", ""));
        result.add(new TestData("苏雨", ""));
        result.add(new TestData("国菜", ""));
        result.add(new TestData("第六强", ""));
        result.add(new TestData("小甜甜", ""));
        return result;
    }

}
