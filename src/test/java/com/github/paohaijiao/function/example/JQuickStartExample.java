package com.github.paohaijiao.function.example;

import com.github.paohaijiao.function.core.JQuickMethodFunctionProvider;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import com.github.paohaijiao.function.string.JQuickToUpperFunctionProvider;
import com.github.paohaijiao.function.string.JQuickToLowerFunctionProvider;
import com.github.paohaijiao.function.string.JQuickTrimFunctionProvider;

import java.util.Arrays;
import java.util.List;

/**
 * JQuickMethodInvocationManager 快速使用示例
 * 
 * 展示如何使用新增的注册功能：
 * 1. 批量注册
 * 2. 覆盖注册
 * 3. Lambda 表达式注册
 *
 * @author Example
 */
public class JQuickStartExample {

    public static void main(String[] args) {
        JQuickMethodInvocationManager manager = JQuickMethodInvocationManager.getInstance();
        //批量注册现有的 FunctionProvider
        exampleBatchRegistration(manager);
        // 使用覆盖注册替换已存在的方法
        exampleReplaceRegistration(manager);
        //使用 Lambda 快速注册简单函数
        exampleLambdaRegistration(manager);

        //创建自定义 FunctionProvider 类
        exampleCustomFunctionProvider(manager);

        //打印所有注册的方法
        System.out.println("\n=== 所有已注册的方法 ===");
        manager.printStats();
    }

    /**
     * 示例 1: 批量注册
     */
    private static void exampleBatchRegistration(JQuickMethodInvocationManager manager) {
        System.out.println("=== 示例 1: 批量注册 ===");
        JQuickToUpperFunctionProvider toUpper = new JQuickToUpperFunctionProvider();
        JQuickToLowerFunctionProvider toLower = new JQuickToLowerFunctionProvider();
        JQuickTrimFunctionProvider trim = new JQuickTrimFunctionProvider();
        List<JQuickMethodFunctionProvider> invokers = Arrays.asList(toUpper, toLower, trim);
        manager.registerInvokers(invokers);
        System.out.println("批量注册完成：toUpper, toLower, trim");
        System.out.println("调用结果: toUpper('hello') = " + manager.invoke("toUpper", "hello"));
    }

    /**
     * 示例 2: 覆盖注册
     */
    private static void exampleReplaceRegistration(JQuickMethodInvocationManager manager) {
        System.out.println("\n=== 示例 2: 覆盖注册 ===");
        JQuickMethodFunctionProvider customUpper = new JQuickBaseFunctionFunctionProvider("toUpper", "[Override] 自定义大写转换") {
            @Override
            public Object invoke(List<Object> args) {
                String str = args.get(0) != null ? args.get(0).toString() : null;
                return str != null ? str.toUpperCase() + "_OVERRIDE" : null;
            }
            @Override
            public int getPriority() {
                return 10000; // 更高优先级
            }
        };
        manager.registerOrReplaceInvoker(customUpper);
        System.out.println("覆盖注册完成: toUpper 方法已被替换");
        System.out.println("调用结果: toUpper('hello') = " + manager.invoke("toUpper", "hello"));
    }

    /**
     * 示例 3: Lambda 表达式注册
     */
    private static void exampleLambdaRegistration(JQuickMethodInvocationManager manager) {
        System.out.println("\n=== 示例 3: Lambda 表达式注册 ===");
        manager.registerInvoker("double", args -> {
            Number num = (Number) args.get(0);
            return num.doubleValue() * 2;
        });
        System.out.println("Lambda 注册: double 方法");
        manager.registerInvoker("triple", args -> {
            Number num = (Number) args.get(0);
            return num.doubleValue() * 3;
        }, "[Math] 将数值乘以3");
        System.out.println("Lambda 注册: triple 方法");
        manager.registerInvoker("quadruple", args -> {
            Number num = (Number) args.get(0);
            return num.doubleValue() * 4;
        }, "[Math] 将数值乘以4", 500);
        System.out.println("Lambda 注册: quadruple 方法");
        manager.registerInvoker("sum", args -> {
            double sum = 0;
            for (Object arg : args) {
                if (arg instanceof Number) {
                    sum += ((Number) arg).doubleValue();
                }
            }
            return sum;
        }, "[Math] 计算多个数的和");
        System.out.println("Lambda 注册: sum 方法");
        System.out.println("调用结果: double(5) = " + manager.invoke("double", 5));
        System.out.println("调用结果: triple(5) = " + manager.invoke("triple", 5));
        System.out.println("调用结果: quadruple(5) = " + manager.invoke("quadruple", 5));
        System.out.println("调用结果: sum(1,2,3,4,5) = " + manager.invoke("sum", 1, 2, 3, 4, 5));
        manager.registerOrReplaceInvoker("customDouble", args -> {
            Number num = (Number) args.get(0);
            return num.doubleValue() * 2 + 100;
        }, "[Math] 自定义翻倍并加100", 1000);
        System.out.println("Lambda 覆盖注册: customDouble 方法");
        System.out.println("调用结果: customDouble(5) = " + manager.invoke("customDouble", 5));
    }

    /**
     * 示例 4: 创建自定义 FunctionProvider 类
     */
    private static void exampleCustomFunctionProvider(JQuickMethodInvocationManager manager) {
        System.out.println("\n=== 示例 4: 自定义 FunctionProvider 类 ===");
        class PowerFunctionProvider extends JQuickBaseFunctionFunctionProvider {
            public PowerFunctionProvider() {
                super("power", "[Math] 计算幂次方");
            }

            @Override
            public Object invoke(List<Object> args) {
                if (args.size() != 2) {
                    throw new IllegalArgumentException("power 方法需要 2 个参数");
                }
                double base = ((Number) args.get(0)).doubleValue();
                double exponent = ((Number) args.get(1)).doubleValue();
                return Math.pow(base, exponent);
            }

            @Override
            public int getPriority() {
                return 800;
            }
        }
        PowerFunctionProvider powerFunc = new PowerFunctionProvider();
        manager.registerInvoker(powerFunc);
        System.out.println("自定义类注册: power 方法");
        System.out.println("调用结果: power(2, 10) = " + manager.invoke("power", 2, 10));
    }
}