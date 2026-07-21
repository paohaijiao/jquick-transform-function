package com.github.paohaijiao.function.math;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;

/**
 * 求和函数 - 计算所有参数的和
 * 支持：整数、浮点数
 * 用法：sum(1, 2, 3, 4) => 10.0
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickSumFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickSumFunctionProvider() {
        super("sum", "[Math] 求和 - 计算所有数值参数的总和");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args == null || args.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Object arg : args) {
            if (null==arg) {
                continue;
            }
            sum += asDouble(arg);
        }
        return sum;
    }
}