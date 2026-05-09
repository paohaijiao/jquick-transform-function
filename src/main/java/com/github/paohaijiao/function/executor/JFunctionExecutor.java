package com.github.paohaijiao.function.executor;


import java.util.List;

/**
 * 函数执行器接口
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
public interface JFunctionExecutor {

    /**
     * 执行函数
     *
     * @param args 参数列表
     * @return 执行结果
     * @throws Exception 执行异常
     */
    Object apply(List<Object> args) throws Exception;
}
