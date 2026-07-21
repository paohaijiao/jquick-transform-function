package com.github.paohaijiao.function.math;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;

/**
 * 计数函数 - 返回参数个数
 * 用法：count(1, 2, 3, 4) => 4
 *      count() => 0
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickCountFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickCountFunctionProvider() {
        super("count", "[Math] 计数 - 返回参数的数量");
    }

    @Override
    public Object invoke(List<Object> args) {
        return args == null ? 0 : args.size();
    }
}
