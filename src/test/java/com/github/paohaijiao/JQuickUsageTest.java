package com.github.paohaijiao;

import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Test;

import java.util.Arrays;

public class JQuickUsageTest {
    @Test
    public void test(){
        JQuickMethodInvocationManager manager = JQuickMethodInvocationManager.getInstance();
        manager.printRegisteredMethods();
        String upper = (String) manager.invoke("toUpper", "hello");
        System.out.println("toUpper: " + upper);  // HELLO

        int length = (int) manager.invoke("length", "hello");
        System.out.println("length: " + length);  // 5

        String substring = (String) manager.invoke("substring", "hello world", 0, 5);
        System.out.println("substring: " + substring);  // hello

        boolean contains = (boolean) manager.invoke("contains", "hello world", "world");
        System.out.println("contains: " + contains);  // true

        String replaced = (String) manager.invoke("replace", "hello world", "world", "java");
        System.out.println("replace: " + replaced);  // hello java

        String[] parts = (String[]) manager.invoke("split", "a,b,c", ",");
        System.out.println("split: " + Arrays.toString(parts));  // [a, b, c]

        String concat = (String) manager.invoke("concat", "Hello", " ", "World", "!");
        System.out.println("concat: " + concat);  // Hello World!

        // 数值方法调用
        double sum = (double) manager.invoke("add", 10, 20, 30);
        System.out.println("add: " + sum);  // 60.0

        double max = (double) manager.invoke("max", 10, 20, 30, 5, 25);
        System.out.println("max: " + max);  // 30.0

        double avg = (double) manager.invoke("avg", 10, 20, 30);
        System.out.println("avg: " + avg);  // 20.0

        double ceil = (double) manager.invoke("ceil", 3.14);
        System.out.println("ceil: " + ceil);  // 4.0

        // 日期方法调用
        System.out.println("now: " + manager.invoke("now"));
        System.out.println("today: " + manager.invoke("today"));
        System.out.println("timestamp: " + manager.invoke("timestamp"));

        // 检查方法是否存在
        System.out.println("Has 'toUpper': " + manager.hasMethod("toUpper"));
        System.out.println("Has 'unknown': " + manager.hasMethod("unknown"));

        // 获取所有支持的方法
        System.out.println("Supported methods: " + manager.getSupportedMethods());
    }
}
