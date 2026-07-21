package com.github.paohaijiao.function.string;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;

/**
 * 字符串聚合函数（类似 SQL 的 STRING_AGG）
 * 用法：stringAgg(",", "a", "b", "c") => "a,b,c"
 *       stringAgg(" - ", "apple", "banana", "orange") => "apple - banana - orange"
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickStringAggFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickStringAggFunctionProvider() {
        super("stringAgg", "[SQL] 字符串聚合 - 使用指定分隔符合并字符串");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args == null || args.size() < 2) {
            throw new IllegalArgumentException("stringAgg() requires at least 2 arguments: separator and values");
        }
        String separator = asString(args.get(0));
        if (separator == null) {
            separator = "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.size(); i++) {
            if (i > 1) {
                sb.append(separator);
            }
            sb.append(asString(args.get(i)));
        }
        return sb.toString();
    }
}
