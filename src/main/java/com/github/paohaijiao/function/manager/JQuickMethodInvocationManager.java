package com.github.paohaijiao.function.manager;

import com.github.paohaijiao.function.core.JQuickMethodInvoker;
import com.github.paohaijiao.spi.ServiceLoader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 方法调用管理器 - 基于你的 SPI 管理所有方法调用
 */
public class JQuickMethodInvocationManager {

    private static volatile JQuickMethodInvocationManager instance;

    private final Map<String, JQuickMethodInvoker> invokerRegistry = new ConcurrentHashMap<>();
    private final List<JQuickMethodInvoker> allInvokers;

    private JQuickMethodInvocationManager() {
        // 使用你的 ServiceLoader 加载所有 MethodInvoker SPI 实现
        this.allInvokers = ServiceLoader.loadServicesByPriority(JQuickMethodInvoker.class);

        // 注册到缓存
        for (JQuickMethodInvoker invoker : allInvokers) {
            invokerRegistry.put(invoker.getMethodName(), invoker);
        }
    }

    public static JQuickMethodInvocationManager getInstance() {
        if (instance == null) {
            synchronized (JQuickMethodInvocationManager.class) {
                if (instance == null) {
                    instance = new JQuickMethodInvocationManager();
                }
            }
        }
        return instance;
    }

    /**
     * 调用方法
     */
    public Object invoke(String methodName, Object... args) {
        List<Object> argList = args == null ? Collections.emptyList() : Arrays.asList(args);
        return invoke(methodName, argList);
    }

    /**
     * 调用方法
     */
    public Object invoke(String methodName, List<Object> args) {
        JQuickMethodInvoker invoker = invokerRegistry.get(methodName);
        if (invoker == null) {
            throw new IllegalArgumentException("Method not found: " + methodName);
        }
        return invoker.invoke(args);
    }

    /**
     * 检查方法是否存在
     */
    public boolean hasMethod(String methodName) {
        return invokerRegistry.containsKey(methodName);
    }

    /**
     * 获取所有支持的方法
     */
    public List<String> getSupportedMethods() {
        return new ArrayList<>(invokerRegistry.keySet());
    }

    /**
     * 动态注册方法
     */
    public void registerInvoker(JQuickMethodInvoker invoker) {
        invokerRegistry.put(invoker.getMethodName(), invoker);
    }

    /**
     * 注销方法
     */
    public void unregisterInvoker(String methodName) {
        invokerRegistry.remove(methodName);
    }

    /**
     * 打印所有注册的方法
     */
    public void printRegisteredMethods() {
        System.out.println("=== Registered Methods ===");
        for (JQuickMethodInvoker invoker : allInvokers) {
            System.out.printf("  %-15s - %s (Priority: %d)%n",
                    invoker.getMethodName(),
                    invoker.getDescription(),
                    invoker.getPriority());
        }
        System.out.printf("Total: %d methods%n", allInvokers.size());
    }
}
