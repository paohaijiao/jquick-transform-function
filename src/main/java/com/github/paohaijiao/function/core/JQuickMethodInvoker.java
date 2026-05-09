package com.github.paohaijiao.function.core;

import java.util.List;

/**
 * 方法调用器接口 - SPI 扩展点
 */
public interface JQuickMethodInvoker {

    /**
     * 获取方法名称
     */
    String getMethodName();

    /**
     * 执行方法调用
     */
    Object invoke(List<Object> args);

    /**
     * 获取方法描述
     */
    default String getDescription() {
        return getMethodName();
    }

    /**
     * 获取参数类型列表
     */
    default List<Class<?>> getParameterTypes() {
        return java.util.Collections.emptyList();
    }

    /**
     * 获取优先级
     */
    default int getPriority() {
        return 5000;
    }
}