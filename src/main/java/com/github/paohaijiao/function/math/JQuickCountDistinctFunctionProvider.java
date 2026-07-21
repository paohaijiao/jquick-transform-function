package com.github.paohaijiao.function.math;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 去重计数函数 - 返回去重后的元素数量
 * 用法：countDistinct(1, 2, 2, 3, 3, 3) => 3
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickCountDistinctFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickCountDistinctFunctionProvider() {
        super("countDistinct", "[SQL] 去重计数 - 返回去重后元素的数量");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args == null || args.isEmpty()) {
            return 0;
        }
        Set<Object> distinctSet = new HashSet<>(args);
        return distinctSet.size();
    }
}
