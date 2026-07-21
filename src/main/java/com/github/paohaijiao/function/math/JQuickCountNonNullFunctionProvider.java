package com.github.paohaijiao.function.math;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;

/**
 * 非空计数函数 - 返回非空参数的数量
 * 用法：countNonNull(1, null, 3, null, 5) => 3
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickCountNonNullFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickCountNonNullFunctionProvider() {
        super("countNonNull", "[Math] 非空计数 - 返回非空参数的数量");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args == null || args.isEmpty()) {
            return 0;
        }
        long count = args.stream().filter(arg -> arg != null).count();
        return (int) count;
    }
}
