package com.github.paohaijiao.function.manager;

import com.github.paohaijiao.function.core.JQuickMethodFunctionProvider;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.function.string.*;

import java.util.Arrays;
import java.util.List;

/**
 * JQuickMethodInvocationManager 注册功能测试示例
 *
 * @author Test
 */
public class JQuickMethodInvocationManagerTest {

    public static void main(String[] args) {
        JQuickMethodInvocationManager manager = JQuickMethodInvocationManager.getInstance();
        System.out.println("=== 测试批量注册功能 ===");
        testBatchRegistration(manager);
        System.out.println("\n=== 测试覆盖注册功能 ===");
        testReplaceRegistration(manager);
        System.out.println("\n=== 测试 Lambda 注册功能 ===");
        testLambdaRegistration(manager);
        System.out.println("\n=== 测试调用注册的方法 ===");
        testInvokeMethods(manager);
        System.out.println("\n=== 打印所有注册的方法 ===");
        manager.printRegisteredMethods();
    }

    /**
     * 测试批量注册
     */
    private static void testBatchRegistration(JQuickMethodInvocationManager manager) {
        JQuickToUpperFunctionProvider toUpper = new JQuickToUpperFunctionProvider();
        JQuickToLowerFunctionProvider toLower = new JQuickToLowerFunctionProvider();
        JQuickTrimFunctionProvider trim = new JQuickTrimFunctionProvider();
        List<JQuickMethodFunctionProvider> invokers = Arrays.asList(toUpper, toLower, trim);
        manager.registerInvokers(invokers);
        System.out.println("批量注册（List方式）完成");
        manager.registerInvokers(toUpper, toLower, trim);
        System.out.println("批量注册（可变参数方式）完成");
    }

    /**
     * 测试覆盖注册
     */
    private static void testReplaceRegistration(JQuickMethodInvocationManager manager) {
        JQuickMethodFunctionProvider customToUpper = new JQuickBaseFunctionFunctionProvider("customToUpper", "[Custom] 自定义大写转换") {
            @Override
            public Object invoke(List<Object> args) {
                String str = args.get(0) != null ? args.get(0).toString() : null;
                return str != null ? "CUSTOM_" + str.toUpperCase() : null;
            }
            @Override
            public int getPriority() {
                return 1000;
            }
        };
        manager.registerOrReplaceInvoker(customToUpper);
        System.out.println("覆盖注册完成: customToUpper");
        Object result = manager.invoke("customToUpper", "hello");
        System.out.println("调用结果: customToUpper('hello') = " + result);
    }

    /**
     * 测试 Lambda 注册
     */
    private static void testLambdaRegistration(JQuickMethodInvocationManager manager) {
        manager.registerInvoker("doubleValue", args -> {
            Number num = (Number) args.get(0);
            return num.doubleValue() * 2;
        });
        System.out.println("Lambda 注册: doubleValue");
        manager.registerInvoker("greet", args -> {
            String name = args.get(0).toString();
            return "Hello, " + name + "!";
        }, "[String] 生成问候语");
        System.out.println("Lambda 注册: greet");
        manager.registerInvoker("calculateSum", args -> {
            double sum = 0;
            for (Object arg : args) {
                sum += ((Number) arg).doubleValue();
            }
            return sum;
        }, "[Math] 计算多参数之和", 100);
        System.out.println("Lambda 注册: calculateSum");
        manager.registerOrReplaceInvoker("toUpperCaseCustom", args ->
                args.get(0).toString().toUpperCase() + "_CUSTOM",
                "[String] 自定义大写转换",
                2000
        );
        System.out.println("Lambda 覆盖注册: toUpperCaseCustom");
    }

    /**
     * 测试调用已注册的方法
     */
    private static void testInvokeMethods(JQuickMethodInvocationManager manager) {
        Object result1 = manager.invoke("toUpper", "hello");
        System.out.println("toUpper('hello') = " + result1);
        Object result2 = manager.invoke("toLower", "WORLD");
        System.out.println("toLower('WORLD') = " + result2);
        Object result3 = manager.invoke("trim", "  test  ");
        System.out.println("trim('  test  ') = " + result3);
        Object result4 = manager.invoke("doubleValue", 5);
        System.out.println("doubleValue(5) = " + result4);
        Object result5 = manager.invoke("greet", "Martin");
        System.out.println("greet('Martin') = " + result5);
        Object result6 = manager.invoke("calculateSum", 1, 2, 3, 4);
        System.out.println("calculateSum(1,2,3,4) = " + result6);
        Object result7 = manager.invoke("customToUpper", "test");
        System.out.println("customToUpper('test') = " + result7);
        Object result8 = manager.invoke("toUpperCaseCustom", "example");
        System.out.println("toUpperCaseCustom('example') = " + result8);
        boolean hasMethod = manager.hasMethod("doubleValue");
        System.out.println("hasMethod('doubleValue') = " + hasMethod);
        List<String> methods = manager.getSupportedMethods();
        System.out.println("支持的方法数量: " + methods.size());
    }
}