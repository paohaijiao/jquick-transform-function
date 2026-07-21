package com.github.paohaijiao.function.math;


import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;

/**
 * 乘积函数 - 计算所有参数的乘积
 * 用法：product(1, 2, 3, 4) => 24.0
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickProductFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickProductFunctionProvider() {
        super("product", "[Math] 乘积 - 计算所有数值参数的乘积");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args == null || args.isEmpty()) {
            return 1.0;
        }
        double product = 1.0;
        for (Object arg : args) {
            if (null==arg) {
                continue;
            }
            product *= asDouble(arg);
        }
        return product;
    }
}
